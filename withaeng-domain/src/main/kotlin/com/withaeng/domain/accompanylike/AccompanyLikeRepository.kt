package com.withaeng.domain.accompanylike

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyLikeRepository : JpaRepository<AccompanyLike, Long> {
    fun countByAccompanyId(accompanyId: Long): Long
    fun findByUserIdAndAccompanyId(userId: Long, accompanyId: Long): AccompanyLike?
}