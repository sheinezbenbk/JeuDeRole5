package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.model.entity.Arme
import org.springframework.data.jpa.repository.JpaRepository


interface ArmeDAO : JpaRepository<Arme, Long> {


    fun findByNomContains(nom: String): List<Arme>

}