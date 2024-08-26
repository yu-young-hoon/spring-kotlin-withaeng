package com.travel.withaeng.applicationservice.accompanyreply

import com.travel.withaeng.applicationservice.accompanyreply.dto.*
import com.travel.withaeng.applicationservice.common.PagingResponse
import com.travel.withaeng.applicationservice.common.toPaging
import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.domain.accompanyreply.AccompanyReplyDto
import com.travel.withaeng.domain.accompanyreply.AccompanyReplyService
import com.travel.withaeng.domain.accompanyreply.AccompanyReplyStatus
import com.travel.withaeng.domain.accompanyreplylike.AccompanyReplyLikeService
import com.travel.withaeng.domain.user.UserService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyReplyApplicationService(
    private val userService: UserService,
    private val accompanyReplyService: AccompanyReplyService,
    private val accompanyReplyLikeService: AccompanyReplyLikeService,
) {

    @Transactional
    fun create(request: CreateAccompanyReplyServiceRequest): AccompanyReplyResponse {
        val userSimpleDto = userService.findById(request.userId)
        return accompanyReplyService.create(
            accompanyId = request.accompanyId,
            userId = request.userId,
            content = request.content,
            parentId = request.parentId
        ).toResponse(userSimpleDto)
    }

    fun getList(accompanyId: Long, pageable: Pageable): PagingResponse<List<FindAccompanyReplyResponse>> {
        val accompanyReplyPage = accompanyReplyService.getList(accompanyId, pageable)
        val accompanyReplyResponseList = accompanyReplyPage
            .map { it.toResponse() }
            .toList()

        return PagingResponse(accompanyReplyResponseList, accompanyReplyPage.toPaging())
    }

    @Transactional
    fun update(request: UpdateAccompanyReplyServiceRequest): AccompanyReplyResponse {
        val accompanyReplyDto = accompanyReplyService.findById(request.accompanyReplyId)
        val userSimpleDto = userService.findById(request.userId)
        validateUpdate(request, accompanyReplyDto)
        val updated = accompanyReplyService.update(
            replyId = accompanyReplyDto.id,
            content = request.content,
        )
        val likeCount = accompanyReplyLikeService.countAccompanyReplyLikeCount(accompanyReplyDto.id)
        return updated.toResponse(userSimpleDto, likeCount)
    }

    @Transactional
    fun delete(userId: Long, accompanyReplyId: Long, parentId: Long? = null) {
        val accompanyReplyDto = accompanyReplyService.findById(accompanyReplyId)
        validateDelete(userId, parentId, accompanyReplyDto)
        accompanyReplyService.delete(accompanyReplyDto.id)
    }

    private fun validateDelete(requestUserId: Long, requestParentId: Long?, accompanyReplyDto: AccompanyReplyDto) {
        validateCreator(accompanyReplyDto.userId, requestUserId)
        validateDeletionStatus(accompanyReplyDto.status)
        validateSubReply(accompanyReplyDto.parentId, requestParentId)
        if (requestParentId != null) {
            validateParentId(requestParentId, accompanyReplyDto.parentId)
        }
    }

    private fun validateDeletionStatus(status: AccompanyReplyStatus) {
        if (status == AccompanyReplyStatus.DELETED) {
            throw WithaengException.of(
                type = WithaengExceptionType.INVALID_INPUT,
                message = "삭제된 댓글 입니다."
            )
        }
    }

    private fun validateUpdate(request: UpdateAccompanyReplyServiceRequest, accompanyReplyDto: AccompanyReplyDto) {
        validateCreator(accompanyReplyDto.userId, request.userId)
        validateSubReply(accompanyReplyDto.parentId, request.parentId)
        if (request.parentId != null) {
            validateParentId(request.parentId, accompanyReplyDto.parentId)
        }
    }

    private fun validateCreator(createUserId: Long, requestUserId: Long) {
        if (createUserId != requestUserId) {
            throw WithaengException.of(
                type = WithaengExceptionType.ACCESS_DENIED,
            )
        }
    }

    private fun validateSubReply(parentId: Long?, requestParentId: Long?) {
        if (parentId != null && requestParentId == null) {
            throw WithaengException.of(
                type = WithaengExceptionType.INVALID_INPUT,
                message = "댓글이 아닙니다."
            )
        }
    }

    private fun validateParentId(parentId: Long, requestParentId: Long?) {
        if (parentId != requestParentId) {
            throw WithaengException.of(
                type = WithaengExceptionType.INVALID_INPUT,
                message = "해당 댓글에 대한 대댓글이 아닙니다."
            )
        }
    }

}
