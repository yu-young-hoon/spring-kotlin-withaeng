package com.travel.withaeng.domain.accompanyreplylike

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyReplyLikeRepository : JpaRepository<AccompanyReplyLike, Long> {
    fun countByReplyId(replyId: Long): Long
    fun findByUserIdAndReplyId(userId: Long, replyId: Long): AccompanyReplyLike?
}