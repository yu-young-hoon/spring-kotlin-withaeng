package com.travel.withaeng.domain.accompanyreply

import com.querydsl.core.annotations.QueryProjection
import com.travel.withaeng.domain.user.UserBasicInfoDto
import java.time.LocalDateTime

data class FindAccompanyReplyDto @QueryProjection constructor(
    val id: Long,
    val accompanyId: Long,
    val parentId: Long? = null,
    val content: String?,
    val status: AccompanyReplyStatus,
    val likeCount: Long = 0,
    val createdAt: LocalDateTime?,
    val author: UserBasicInfoDto,
)
