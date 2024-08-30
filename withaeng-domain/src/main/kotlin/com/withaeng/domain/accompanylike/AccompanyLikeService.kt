package com.withaeng.domain.accompanylike

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.accompany.AccompanyRepository
import com.withaeng.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyLikeService(
    private val userRepository: UserRepository,
    private val accompanyRepository: AccompanyRepository,
    private val accompanyLikeRepository: AccompanyLikeRepository
) {

    @Transactional
    fun createAccompanyLike(userId: Long, accompanyId: Long) {
        userRepository.findByIdOrNull(userId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 유저를 찾을 수 없습니다."
        )
        accompanyRepository.findByIdOrNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 동행을 찾을 수 없습니다."
        )
        val prevAccompanyLike = accompanyLikeRepository.findByUserIdAndAccompanyId(userId, accompanyId)
        if (prevAccompanyLike != null) return
        val accompanyLike = AccompanyLike.create(userId = userId, accompanyId = accompanyId)
        accompanyLikeRepository.save(accompanyLike)
    }

    fun countByAccompanyId(accompanyId: Long): Long {
        return accompanyLikeRepository.countByAccompanyId(accompanyId)
    }

    @Transactional
    fun deleteAccompanyLike(userId: Long, accompanyId: Long) {
        val accompanyLike = accompanyLikeRepository.findByUserIdAndAccompanyId(
            userId = userId,
            accompanyId = accompanyId
        ) ?: return
        accompanyLikeRepository.delete(accompanyLike)
    }
}