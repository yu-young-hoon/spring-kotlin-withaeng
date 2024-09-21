package com.withaeng.domain.verificationemail

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface VerificationEmailRepository : JpaRepository<VerificationEmail, Long> {
    fun findAllByStatusNot(status: VerificationEmailStatus): List<VerificationEmail>

    fun findByEmail(email: String): VerificationEmail?

    @Modifying
    @Query("UPDATE VerificationEmail v SET v.status = :status WHERE v.id in :ids")
    fun updateStatusByIds(ids: Set<Long>, status: VerificationEmailStatus): Int

    fun deleteAllByUserId(userId: Long)

    fun deleteAllByUserIdAndType(id: Long, status: VerificationEmailType)
}
