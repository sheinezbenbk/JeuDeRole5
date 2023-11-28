package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.*


@Entity
class Utilisateur(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var email : String,
    var mdp: String,


    @OneToMany(mappedBy = "auteur")
    var campagnes: List<Campagne>? = null,

    @ManyToMany
    @JoinTable(
        name = "utilisateur_role",
        joinColumns = [JoinColumn(name = "utilisateur_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: List<Role>? = null

) {
}