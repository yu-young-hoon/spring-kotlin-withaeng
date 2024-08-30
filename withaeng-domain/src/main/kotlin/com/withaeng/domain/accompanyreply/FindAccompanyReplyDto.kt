package com.withaeng.domain.accompanyreply

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

data class FindAccompanyReplyDto @QueryProjection constructor(
    val id: Long,
    val accompanyId: Long,
    val parentId: Long? = null,
    val content: String?,
    val status: AccompanyReplyStatus,
    val likeCount: Long = 0,
    val createdAt: LocalDateTime?,
    val author: FindAccompanyReplyUserInfoDto,
)
