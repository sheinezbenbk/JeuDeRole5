package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.*

@Entity
class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    val nom : String,

    @ManyToMany(mappedBy = "roles")
    var utilisateur: List<Utilisateur>? = null
){
}