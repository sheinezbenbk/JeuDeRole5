package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.ldv.sprintbootaventure.model.entity.Qualite
import org.ldv.sprintbootaventure.model.entity.Item
import org.ldv.sprintbootaventure.model.entity.TypeArmure

@Entity
@DiscriminatorValue("armure")
class Armure constructor(
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
    @JoinColumn(name = "type_armure_id")
    var typeArmure: TypeArmure? = null
) : Item(id, nom, description, cheminImage) {


    fun calculProtection(): Int {
        val protection = this.typeArmure!!.bonusType + this.qualite!!.bonusQualite //variable à potentiel changement

        println(protection)
        return protection
    }

    override fun utiliser(cible: Personnage):String {
        return cible.equipe(this)
    }



}