package com.travel.withaeng.domain.accompanyreply

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyReplyRepository : JpaRepository<AccompanyReply, Long>, AccompanyReplyRepositoryCustom {
    fun findByReplyId(replyId: Long): AccompanyReply?

}