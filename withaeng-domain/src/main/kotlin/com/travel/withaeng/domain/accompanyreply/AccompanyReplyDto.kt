package com.travel.withaeng.domain.accompanyreply

import com.querydsl.core.annotations.QueryProjection

data class AccompanyReplyDto @QueryProjection constructor(
    val id: Long,
    val userId: Long,
    val accompanyId: Long,
    val parentId: Long? = null,
    val content: String
)

fun AccompanyReply.toDto(): AccompanyReplyDto = AccompanyReplyDto(
    id = id,
    userId = userId,
    accompanyId = accompanyId,
    parentId = parentId,
    content = content
)
