package com.travel.withaeng.domain.validateemail

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface ValidatingEmailRepository : JpaRepository<ValidatingEmail, Long> {
    fun findAllByStatusNot(status: ValidatingEmailStatus): List<ValidatingEmail>

    @Modifying
    @Query("UPDATE ValidatingEmail v SET v.status = :status WHERE v.id in :ids")
    fun updateStatusByIds(ids: Set<Long>, status: ValidatingEmailStatus)
}