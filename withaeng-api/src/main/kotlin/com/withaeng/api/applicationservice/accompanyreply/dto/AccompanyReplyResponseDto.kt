package com.withaeng.api.applicationservice.accompanyreply.dto

import com.withaeng.api.applicationservice.user.dto.UserSimpleServiceResponse
import com.withaeng.api.applicationservice.user.dto.toSimpleResponse
import com.withaeng.domain.accompanyreply.AccompanyReplyDto
import com.withaeng.domain.accompanyreply.AccompanyReplyStatus
import com.withaeng.domain.user.dto.UserSimpleDto
import java.time.LocalDateTime

data class AccompanyReplyResponse(
    val id: Long,
    val author: UserSimpleServiceResponse,
    val accompanyId: Long,
    val parentId: Long? = null,
    val content: String?,
    val likeCount: Long = 0L,
    val createdAt: LocalDateTime?,
    val status: AccompanyReplyStatus,
)

fun AccompanyReplyDto.toResponse(userSimpleDto: UserSimpleDto, likeCount: Long = 0L): AccompanyReplyResponse {
    return AccompanyReplyResponse(
        id = id,
        accompanyId = accompanyId,
        parentId = parentId,
        author = userSimpleDto.toSimpleResponse(),
        content = content,
        likeCount = likeCount,
        createdAt = createdAt,
        status = status,
    )
}
