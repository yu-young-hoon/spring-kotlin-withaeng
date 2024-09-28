package com.withaeng.api.applicationservice.accompanyreplylike

import com.withaeng.domain.accompanyreplylike.AccompanyReplyLikeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyReplyLikeApplicationService(
    private val accompanyReplyLikeService: AccompanyReplyLikeService
) {

    @Transactional
    fun like(userId: Long, accompanyReplyId: Long) {
        accompanyReplyLikeService.createAccompanyReplyLike(userId, accompanyReplyId)
    }

    @Transactional
    fun dislike(userId: Long, accompanyReplyId: Long) {
        accompanyReplyLikeService.deleteAccompanyReplyLike(userId, accompanyReplyId)
    }
}