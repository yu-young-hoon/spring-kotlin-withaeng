package com.travel.withaeng.domain.accompanyreply

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

data class AccompanyReplyDto @QueryProjection constructor(
    val id: Long,
    val userId: Long,
    val accompanyId: Long,
    val parentId: Long? = null,
    val content: String,
    val createdAt: LocalDateTime,
    val status: AccompanyReplyStatus,
)

fun AccompanyReply.toDto(): AccompanyReplyDto = AccompanyReplyDto(
    id = id,
    userId = userId,
    accompanyId = accompanyId,
    parentId = parentId,
    content = content,
    createdAt = createdAt,
    status = status,
)
