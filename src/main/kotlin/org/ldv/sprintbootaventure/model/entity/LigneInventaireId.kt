package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class LigneInventaireId(
    val personnageId: Long,
    val itemId: Long

) : Serializable

