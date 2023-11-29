package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.model.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemDAO : JpaRepository<Item,Long> {
}