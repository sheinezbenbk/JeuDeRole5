package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.*

@Entity
class TypeAccessoire(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    var id:Long? = null,
    var nom:String,
    var typebonus:String
) {
}