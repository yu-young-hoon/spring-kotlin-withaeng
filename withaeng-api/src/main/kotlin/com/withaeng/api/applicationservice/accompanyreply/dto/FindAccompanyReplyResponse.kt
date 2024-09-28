package com.withaeng.api.applicationservice.accompanyreply.dto

import com.withaeng.domain.accompanyreply.AccompanyReplyStatus
import com.withaeng.domain.accompanyreply.FindAccompanyReplyDto
import com.withaeng.domain.accompanyreply.FindAccompanyReplyUserInfoDto
import java.time.LocalDateTime

data class FindAccompanyReplyResponse(
    val id: Long,
    val author: FindAccompanyReplyUserInfoDto,
    val accompanyId: Long,
    val parentId: Long? = null,
    val content: String?,
    val likeCount: Long = 0L,
    val createdAt: LocalDateTime?,
    val status: AccompanyReplyStatus,
)

fun FindAccompanyReplyDto.toResponse(): FindAccompanyReplyResponse {
    return FindAccompanyReplyResponse(
        id = id,
        accompanyId = accompanyId,
        parentId = parentId,
        author = author,
        content = content,
        likeCount = likeCount,
        createdAt = createdAt,
        status = status,
    )
}