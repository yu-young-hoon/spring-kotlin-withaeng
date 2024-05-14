package com.travel.withaeng.applicationservice.accompanyreply.dto

import com.travel.withaeng.domain.accompanyreply.AccompanyReplyDto

data class AccompanyReplyResponse(
    val id: Long,
    val accompanyId: Long,
    val parentId: Long? = null,
    val userId: Long,
    val content: String,
    val likeCount: Long = 0L
)

fun AccompanyReplyDto.toResponse(likeCount: Long = 0L): AccompanyReplyResponse {
    return AccompanyReplyResponse(
        id = id,
        accompanyId = accompanyId,
        parentId = parentId,
        userId = userId,
        content = content,
        likeCount = likeCount
    )
}