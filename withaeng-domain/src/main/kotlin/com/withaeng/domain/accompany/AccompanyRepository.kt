package com.withaeng.domain.accompany

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AccompanyRepository : JpaRepository<Accompany, Long>, AccompanyRepositoryCustom {

    @Query("SELECT a FROM Accompany a WHERE a.deletedAt is null ORDER BY a.id DESC")
    fun findAllOrderByDesc(): List<Accompany>

    fun countByUserId(userId: Long): Int

    fun findByIdAndDeletedAtIsNull(id: Long): Accompany?
}