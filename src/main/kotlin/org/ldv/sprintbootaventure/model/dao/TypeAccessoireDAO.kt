package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.model.entity.TypeAccessoire
import org.springframework.data.jpa.repository.JpaRepository


interface TypeAccessoireDAO: JpaRepository<TypeAccessoire, Long> {

}