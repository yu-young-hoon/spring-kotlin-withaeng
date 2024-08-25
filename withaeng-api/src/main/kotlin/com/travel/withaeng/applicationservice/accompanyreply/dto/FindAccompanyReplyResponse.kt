package com.travel.withaeng.applicationservice.accompanyreply.dto

import com.travel.withaeng.domain.accompanyreply.AccompanyReplyStatus
import com.travel.withaeng.domain.accompanyreply.FindAccompanyReplyDto
import com.travel.withaeng.domain.user.UserBasicInfoDto
import java.time.LocalDateTime

data class FindAccompanyReplyResponse(
    val id: Long,
    val author: UserBasicInfoDto,
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