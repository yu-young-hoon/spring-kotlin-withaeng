package com.travel.withaeng.domain.accompanyreplylike

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyReplyLikeRepository : JpaRepository<AccompanyReplyLikeEntity, Long>,
    AccompanyReplyLikeRepositoryCustom {

    fun countByReplyId(replyId: Long): Long

    fun findByReplyIdAndUserId(replyId: Long, userId: Long): AccompanyReplyLikeEntity?
}