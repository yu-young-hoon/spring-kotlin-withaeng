package com.travel.withaeng.domain.tag

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "tags")
@Entity
class Tag (
    @Column(name = "name", nullable = false)
    val name: String
): BaseEntity()