package com.travel.withaeng.applicationservice.accompanyreply

import com.travel.withaeng.applicationservice.accompanyreply.dto.AccompanyReplyResponse
import com.travel.withaeng.applicationservice.accompanyreply.dto.CreateAccompanyReplyServiceRequest
import com.travel.withaeng.applicationservice.accompanyreply.dto.UpdateAccompanyReplyServiceRequest
import com.travel.withaeng.applicationservice.accompanyreply.dto.toResponse
import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.domain.accompanyreply.AccompanyReplyService
import com.travel.withaeng.domain.accompanyreplylike.AccompanyReplyLikeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyReplyApplicationService(
    val accompanyReplyService: AccompanyReplyService,
    val accompanyReplyLikeService: AccompanyReplyLikeService
) {

    @Transactional
    fun create(request: CreateAccompanyReplyServiceRequest): AccompanyReplyResponse {
        return accompanyReplyService.create(
            accompanyId = request.accompanyId,
            userId = request.userId,
            content = request.content,
            parentId = request.parentId
        ).toResponse()
    }

    fun findById(accompanyReplyId: Long): AccompanyReplyResponse {
        val accompanyReplyDto = accompanyReplyService.findById(accompanyReplyId)
        val likeCount = accompanyReplyLikeService.countAccompanyReplyLikeCount(accompanyReplyDto.id)
        return accompanyReplyDto.toResponse(likeCount)
    }

    fun findAll(accompanyId: Long): List<AccompanyReplyResponse> {
        val accompanyReplyDtoList = accompanyReplyService.findAllByAccompanyId(accompanyId)
        // TODO: Fix getting like count logic for using bulk from single
        return accompanyReplyDtoList.map { replyDto ->
            val likeCount = accompanyReplyLikeService.countAccompanyReplyLikeCount(replyDto.id)
            replyDto.toResponse(likeCount)
        }
    }

    @Transactional
    fun update(request: UpdateAccompanyReplyServiceRequest): AccompanyReplyResponse {
        val accompanyReplyDto = accompanyReplyService.findById(request.accompanyReplyId)
        if (accompanyReplyDto.userId != request.userId) {
            throw WithaengException.of(
                type = WithaengExceptionType.ACCESS_DENIED,
                message = "댓글 작성자가 아닌 사용자가 수정할 수 없습니다."
            )
        }
        val updated = accompanyReplyService.update(accompanyReplyDto.id, request.content)
        val likeCount = accompanyReplyLikeService.countAccompanyReplyLikeCount(accompanyReplyDto.id)
        return updated.toResponse(likeCount)
    }

    @Transactional
    fun delete(userId: Long, accompanyReplyId: Long) {
        val accompanyReplyDto = accompanyReplyService.findById(accompanyReplyId)
        if (accompanyReplyDto.userId != userId) {
            throw WithaengException.of(
                type = WithaengExceptionType.ACCESS_DENIED,
                message = "댓글 작성자가 아닌 사용자가 삭제할 수 없습니다."
            )
        }
        accompanyReplyService.delete(accompanyReplyDto.id)
    }
}