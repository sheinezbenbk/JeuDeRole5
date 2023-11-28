package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.model.entity.Accessoire
import org.springframework.data.jpa.repository.JpaRepository


interface AccessoireDAO: JpaRepository<Accessoire, Long> {


    fun findByNomContains(nom: String): List<Accessoire>

}