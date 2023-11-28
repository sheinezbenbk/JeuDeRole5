package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.model.entity.TypeArmure
import org.springframework.data.jpa.repository.JpaRepository

interface TypeArmureDAO :JpaRepository<TypeArmure, Long> {
}