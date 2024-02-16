package com.travel.withaeng.domain.accompanyreply

data class AccompanyReplyDto(
    val accompanyId: Long,
    val userId: Long,
    val content: String,
    val depth: Long,
    val groupId: Long
)

fun AccompanyReply.toDto(): AccompanyReplyDto = AccompanyReplyDto(
    accompanyId = accompanyId,
    userId = userId,
    content = content,
    depth = depth,
    groupId = groupId
)