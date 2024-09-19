package com.withaeng.domain.accompanyjoinrequests

import com.withaeng.domain.accompanyrequests.AccompanyJoinRequest
import com.withaeng.domain.accompanyrequests.AccompanyJoinRequestStatus
import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyJoinRequestRepository : JpaRepository<AccompanyJoinRequest, Long>,
    AccompanyJoinRequestRepositoryCustom {
    fun countByAccompanyIdAndStatus(
        accompanyId: Long,
        status: AccompanyJoinRequestStatus = AccompanyJoinRequestStatus.ACCEPT,
    ): Int

    fun existsByAccompanyIdAndUserId(accompanyId: Long, userId: Long): Boolean

    fun findByAccompanyId(accompanyId: Long): List<AccompanyJoinRequest>
}