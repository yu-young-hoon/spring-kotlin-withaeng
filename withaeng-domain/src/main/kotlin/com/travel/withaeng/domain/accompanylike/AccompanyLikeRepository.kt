package com.travel.withaeng.domain.accompanylike

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyLikeRepository : JpaRepository<AccompanyLike, Long> {
    fun countByAccompanyId(accompanyId: Long): Long
    fun countByAccompanyIdIsIn(accompanyIds: Set<Long>): Long
}