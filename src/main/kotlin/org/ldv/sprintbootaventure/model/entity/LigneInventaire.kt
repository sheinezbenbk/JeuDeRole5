package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

@Entity
class LigneInventaire (

    @EmbeddedId
    var ligneInventaireId : LigneInventaireId? = null,

    @MapsId("personnageId")
    @ManyToOne
    @JoinColumn(name = "personnageId")
    var personnage: Personnage? = null,


    @MapsId("itemId")
    @ManyToOne
    @JoinColumn(name = "itemId")
    var item: Item? = null,

    var id: Long,
    var nom: String,
    var description: String,
    var quantite: String,

)