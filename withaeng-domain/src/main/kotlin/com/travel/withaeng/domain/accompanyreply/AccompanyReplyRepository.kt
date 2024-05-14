package com.travel.withaeng.domain.accompanyreply

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyReplyRepository : JpaRepository<AccompanyReply, Long> {
    fun findAllByAccompanyId(accompanyId: Long): List<AccompanyReply>
}