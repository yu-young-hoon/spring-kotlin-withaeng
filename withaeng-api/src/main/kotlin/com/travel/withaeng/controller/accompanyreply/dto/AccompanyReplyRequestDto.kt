package com.travel.withaeng.controller.accompanyreply.dto

import com.travel.withaeng.applicationservice.accompanyreply.dto.CreateAccompanyReplyServiceRequest
import com.travel.withaeng.applicationservice.accompanyreply.dto.UpdateAccompanyReplyServiceRequest
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "[Request] 동행 댓글 생성")
data class CreateAccompanyReplyRequest(
    @Schema(description = "동행 댓글 내용")
    val content: String
)

fun CreateAccompanyReplyRequest.toServiceRequest(
    userId: Long,
    accompanyId: Long,
    parentId: Long? = null
): CreateAccompanyReplyServiceRequest = CreateAccompanyReplyServiceRequest(
    userId = userId,
    accompanyId = accompanyId,
    content = content,
    parentId = parentId
)


@Schema(description = "[Request] 동행 댓글 수정")
data class UpdateAccompanyReplyRequest(
    @Schema(description = "동행 댓글 내용")
    val content: String
)

fun UpdateAccompanyReplyRequest.toServiceRequest(
    userId: Long,
    accompanyId: Long,
    accompanyReplyId: Long,
    parentId: Long? = null
): UpdateAccompanyReplyServiceRequest = UpdateAccompanyReplyServiceRequest(
    accompanyReplyId = accompanyReplyId,
    userId = userId,
    accompanyId = accompanyId,
    content = content,
    parentId = parentId,
)