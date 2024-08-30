package com.withaeng.domain.accompanyreply

import com.querydsl.core.annotations.QueryProjection

data class FindAccompanyReplyUserInfoDto @QueryProjection constructor(
    val id: Long,
    val email: String,
    val nickname: String,
)

