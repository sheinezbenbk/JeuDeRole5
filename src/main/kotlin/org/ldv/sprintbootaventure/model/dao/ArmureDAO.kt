package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.model.entity.Armure
import org.springframework.data.jpa.repository.JpaRepository


interface ArmureDAO : JpaRepository<Armure, Long> {


    fun findByNomContains(nom: String): List<Armure>

}