package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.model.entity.Campagne
import org.springframework.data.jpa.repository.JpaRepository

interface CampagneDAO : JpaRepository<Campagne, Long> {
}