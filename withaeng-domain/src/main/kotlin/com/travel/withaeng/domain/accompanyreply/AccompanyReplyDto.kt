package com.travel.withaeng.domain.accompanyreply

data class AccompanyReplyDto(
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
