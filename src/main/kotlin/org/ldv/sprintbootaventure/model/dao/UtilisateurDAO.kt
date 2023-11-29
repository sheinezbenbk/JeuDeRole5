package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.model.entity.Utilisateur
import org.springframework.data.jpa.repository.JpaRepository

interface UtilisateurDAO : JpaRepository<Utilisateur, Long> {
}