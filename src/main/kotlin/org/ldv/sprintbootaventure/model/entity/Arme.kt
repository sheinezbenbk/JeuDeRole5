package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.ldv.sprintbootaventure.model.entity.Qualite
import org.ldv.sprintbootaventure.model.entity.Item
import org.ldv.sprintbootaventure.model.entity.TypeArme

@Entity
@DiscriminatorValue("arme")
class Arme (
    id: Long? = null,
    nom: String,
    description: String,
    cheminImage: String?,
//TODO Attributs spécifiques aux armes
    //Association entre org.ldv.sprintbootaventure.model.entity.Arme et Qualite
    //Plusieurs armes peuvent être rataché a une qualite
    @ManyToOne
    @JoinColumn(name = "qualite_id")
    var qualite: Qualite? = null,
    //TODO Faire l'association avec TypeArme
    @ManyToOne
    @JoinColumn(name = "typeArme_arme_id")
    var typeArme: TypeArme? = null
) : Item(id, nom, description, cheminImage){
    fun calculerDegats(): Int {

        var degats = 0
        val deDegats = TirageDeDes(
            this.typeArme!!.nombreDes,
            this.typeArme!!.valeurDeMax
        )         //Initialisation du dé à utiliser pour les dégats
        val resultatDegats =
            deDegats.lance()           // Utilisation de la méthode lance() pour obtenir le résultat du lancé DEGATS
        val deCritique =
            TirageDeDes(1, 20)      //Initialisation du dé à utiliser pour savoir si c'est un coup critique ou pas
        val resultatCritique =
            deCritique.lance()       // Utilisation de la méthode lance() pour obtenir le résultat du lancé CRITIQUE

        degats = resultatDegats


        if (resultatCritique > this.typeArme!!.activationCritique) {
            degats *= 2
        }

        return degats
    }

    override fun utiliser(cible: Personnage):String {
        return cible.equipe(this)
    }

}