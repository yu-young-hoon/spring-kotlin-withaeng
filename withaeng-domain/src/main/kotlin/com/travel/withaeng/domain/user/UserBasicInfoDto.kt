package com.travel.withaeng.domain.user

import com.querydsl.core.annotations.QueryProjection

data class UserBasicInfoDto @QueryProjection constructor(
    val id: Long,
    val email: String,
    val nickname: String,
)

