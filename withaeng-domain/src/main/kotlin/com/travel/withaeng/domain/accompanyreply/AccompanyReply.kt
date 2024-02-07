package com.travel.withaeng.domain.accompanyreply

data class AccompanyReply(
    val accompanyId: Long,
    val userId: Long,
    val content: String,
    val depth: Long,
    val groupId: Long
)

fun AccompanyReplyEntity.toDto(): AccompanyReply = AccompanyReply(
    accompanyId = accompanyId,
    userId = userId,
    content = content,
    depth = depth,
    groupId = groupId
)