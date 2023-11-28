package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.*

@Entity
class Combat (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nombreTours: Int,
    var estTermine: Boolean,

    @ManyToOne
    @JoinColumn(name ="personageId")
    var monstre: Personnage? = null
)