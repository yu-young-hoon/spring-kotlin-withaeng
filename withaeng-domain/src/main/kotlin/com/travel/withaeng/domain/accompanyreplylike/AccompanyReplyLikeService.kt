package com.travel.withaeng.domain.accompanyreplylike

import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class AccompanyReplyLikeService(
    private val accompanyReplyLikeRepository: AccompanyReplyLikeRepository,
    private val accompanyReplyLikeHistRepository: AccompanyReplyLikeHistRepository
) {

    @Transactional
    fun likeAccompanyReply(userId: Long, accompanyReplyId: Long) {

    }

    fun countAccompanyReplyLikeCount(accompanyReplyId: Long): Long {
        return accompanyReplyLikeRepository.countByReplyId(accompanyReplyId)
    }

    //TODO delete 성능이 안나올 경우 boolean 형으로 좋아요 제어
    @Transactional
    fun deleteAccompanyReplyLike(param: DeleteAccompanyReplyLikeDTO): DeleteAccompanyReplyLikeDTO {

        val accompanyReplyLikeEntity = param.toEntity()
        accompanyReplyLikeRepository.delete(accompanyReplyLikeEntity)

        val accompanyReplyLikeHistEntity = param.toHistEntity(accompanyReplyLikeEntity)
        accompanyReplyLikeHistEntity.deletedAt = LocalDateTime.now()
        accompanyReplyLikeHistRepository.save(accompanyReplyLikeHistEntity)

        return param
    }
}