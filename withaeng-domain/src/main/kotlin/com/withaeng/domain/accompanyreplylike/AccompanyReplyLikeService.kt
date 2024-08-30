package com.withaeng.domain.accompanyreplylike

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.accompanyreply.AccompanyReplyRepository
import com.withaeng.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyReplyLikeService(
    private val userRepository: UserRepository,
    private val accompanyReplyRepository: AccompanyReplyRepository,
    private val accompanyReplyLikeRepository: AccompanyReplyLikeRepository,
) {

    @Transactional
    fun createAccompanyReplyLike(userId: Long, accompanyReplyId: Long) {
        userRepository.findByIdOrNull(userId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 유저가 없습니다."
        )
        accompanyReplyRepository.findByIdOrNull(accompanyReplyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 댓글이 없습니다."
        )
        if (accompanyReplyLikeRepository.findByUserIdAndReplyId(userId, accompanyReplyId) != null) return
        accompanyReplyLikeRepository.save(
            AccompanyReplyLike(
                replyId = accompanyReplyId,
                userId = userId
            )
        )
    }

    fun countAccompanyReplyLikeCount(accompanyReplyId: Long): Long {
        return accompanyReplyLikeRepository.countByReplyId(accompanyReplyId)
    }

    @Transactional
    fun deleteAccompanyReplyLike(userId: Long, accompanyReplyId: Long) {
        val accompanyReplyLike = accompanyReplyLikeRepository.findByUserIdAndReplyId(
            userId = userId,
            replyId = accompanyReplyId
        ) ?: return
        accompanyReplyLikeRepository.delete(accompanyReplyLike)
    }
}