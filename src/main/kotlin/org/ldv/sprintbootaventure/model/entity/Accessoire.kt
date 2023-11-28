package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.ldv.sprintbootaventure.model.entity.Qualite
import org.ldv.sprintbootaventure.model.entity.Item
import org.ldv.sprintbootaventure.model.entity.TypeAccessoire

@Entity
@DiscriminatorValue("accessoire")
class Accessoire constructor(
    id: Long? = null,
    nom: String,
    description: String,
    cheminImage: String?,
//TODO Attributs spécifiques aux accessoires
    //Association entre org.ldv.sprintbootaventure.model.entity.Accessoire et Qualite
    //Plusieurs armes peuvent être rataché a une qualite
    @ManyToOne
    @JoinColumn(name = "qualite_id")
    var qualite: Qualite? = null,
    //TODO Faire l'association avec TypeArme
    @ManyToOne
    @JoinColumn(name = "type_accessoire_id")
    var typeAccessoire : TypeAccessoire? = null
) : Item(id, nom, description, cheminImage) {
}