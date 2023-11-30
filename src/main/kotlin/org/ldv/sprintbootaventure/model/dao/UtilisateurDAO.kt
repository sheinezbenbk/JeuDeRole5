package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.model.entity.Utilisateur
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UtilisateurDAO : JpaRepository<Utilisateur, Long> {

    @Query("select u from Utilisateur u where upper(u.email) = upper(?1)")
    fun findByEmail(email: String): Utilisateur?
}