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
    @JoinColumn(name = "type_arme_id")
    var typeArme: TypeArme? = null
) : Item(id, nom, description, cheminImage){
}