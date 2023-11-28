package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.*


@Entity
class Personnage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    val nom: String,
    val pointDeVie: Int,
    val attaque: Int,
    val defense: Int,
    val endurance: Int,
    val vitesse: Int,
    val cheminImage: String,
    @OneToMany(mappedBy = "personnage")
    var ligneInventaire: List<LigneInventaire> = mutableListOf(),

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

    @OneToMany(mappedBy = "personnage", orphanRemoval = true)
    var campagnes: MutableList<Campagne> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    var utilisateur: Utilisateur? = null,

    ){
    var pointDeVieMax: Int = 0
        get() = 50 + (10 * (this.endurance))
}