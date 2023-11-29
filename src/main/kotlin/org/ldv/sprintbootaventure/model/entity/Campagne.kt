package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Campagne (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom: String?= null,
    var score: Int,
    var etat: String,
    var dernierScore: Int,
    var meilleurScore: Int,

    @ManyToOne
    @JoinColumn(name ="personnageId")
    var hero: Personnage? = null,

    @ManyToOne
    @JoinColumn(name ="utilisateur id")
    var auteur: Utilisateur? = null,

    @OneToMany(mappedBy = "jeu")
    var combats: MutableList<Combat> = mutableListOf(),


    ){

}