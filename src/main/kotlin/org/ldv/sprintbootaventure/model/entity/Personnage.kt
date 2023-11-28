package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.*


@Entity
class Personnage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    val nom: String,

    val attaque: Int,
    val defense: Int,
    val endurance: Int,
    val vitesse: Int,
    val cheminImage: String,
    @OneToMany(mappedBy = "personnage")
    var ligneInventaire: MutableList<LigneInventaire> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "armure_id")
    var armure: Armure? = null,

    @ManyToOne
    @JoinColumn(name = "arme_id")
    var arme: Arme? = null,

    @ManyToOne
    @JoinColumn(name = "accessoire_id")
    var accessoire: Accessoire? = null,

    @OneToMany(mappedBy = "monstre", orphanRemoval = true)
    var combats: MutableList<Combat> = mutableListOf(),

    @OneToMany(mappedBy = "hero", orphanRemoval = true)
    var campagnes: MutableList<Campagne> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    var utilisateur: Utilisateur? = null,

    ) {

    var pointDeVieMax: Int = 0
        get() = 50 + (10 * (this.endurance))

    var pointDeVie: Int = this.pointDeVieMax
        set(value) {
            field = minOf(value, this.pointDeVieMax)
        }

    open fun attaquer(adversaire: Personnage):String {
        // Vérifier si le personnage a une arme équipée
        var degats = this.attaque / 2
        if (arme != null) {
            // Calculer les dégâts en utilisant les attributs du personnage et la méthode calculerDegat de l'arme
            degats += this.arme!!.calculerDegats()
        }
        // Appliquer la défense de l'adversaire (au minimum au moins 1 de dégat)
        val degatsInfliges = maxOf(1, degats - adversaire.calculeDefense())


        // Appliquer les dégâts à l'adversaire
        adversaire.pointDeVie = adversaire.pointDeVie - degatsInfliges

        return("$nom attaque ${adversaire.nom} avec ${arme?.nom ?: "une attaque de base"} et inflige $degatsInfliges points de dégâts.")



    }
    fun calculeDefense(): Int {
        //TODO Mission 4.2
        var result = this.defense / 2;
        if (armure != null) {
            result +=this.armure!!.calculProtection()
        }
        return result;
    }

    /**
     * Ajoute une ligne d'inventaire pour l'item spécifié avec la quantité donnée.
     * Si une ligne d'inventaire pour cet item existe déjà, met à jour la quantité.
     * Si la quantité résultante est inférieure ou égale à zéro, la ligne d'inventaire est supprimée.
     *
     * @param unItem L'item pour lequel ajouter ou mettre à jour la ligne d'inventaire.
     * @param uneQuantite La quantité à ajouter à la ligne d'inventaire existante ou à la nouvelle ligne.
     */
    fun ajouterLigneInventaire(unItem: Item, uneQuantite: Int) {
        // Chercher une ligne d'inventaire existante pour l'item spécifié
        val ligneItem = this.ligneInventaire.find { ligneInventaire -> ligneInventaire.item == unItem }

        // Si aucune ligne d'inventaire n'est trouvée, en créer une nouvelle
        if (ligneItem == null) {
            // Créer un nouvel identifiant pour la ligne d'inventaire
            val ligneInventaireId = LigneInventaireId(this.id!!, unItem.id!!)

            // Ajouter une nouvelle ligne d'inventaire à la liste
            this.ligneInventaire.add(LigneInventaire(ligneInventaireId, this, unItem ,uneQuantite))
        } else {
            // Si une ligne d'inventaire existante est trouvée, mettre à jour la quantité
            ligneItem.quantite += uneQuantite

            // Si la quantité résultante est inférieure ou égale à zéro, supprimer la ligne d'inventaire
            if (ligneItem.quantite <= 0) {
                this.ligneInventaire.remove(ligneItem)
            }
        }
    }
}