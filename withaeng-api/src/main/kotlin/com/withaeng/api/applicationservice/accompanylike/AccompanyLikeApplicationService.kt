package com.withaeng.api.applicationservice.accompanylike

import com.withaeng.domain.accompanylike.AccompanyLikeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AccompanyLikeApplicationService(
    private val accompanyLikeService: AccompanyLikeService
) {

    @Transactional
    fun like(userId: Long, accompanyId: Long) {
        accompanyLikeService.createAccompanyLike(userId, accompanyId)
    }

    @Transactional
    fun dislike(userId: Long, accompanyId: Long) {
        accompanyLikeService.deleteAccompanyLike(userId, accompanyId)
    }

}