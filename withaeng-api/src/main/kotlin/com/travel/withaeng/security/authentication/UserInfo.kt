package com.travel.withaeng.security.authentication

import com.travel.withaeng.domain.user.UserRole
import com.travel.withaeng.domain.user.UserSimpleDto

data class UserInfo(
    val id: Long,
    val email: String,
    val roles: Set<UserRole>,
) {
    companion object {
        fun from(user: UserSimpleDto): UserInfo {
            return UserInfo(
                id = user.id,
                email = user.email,
                roles = user.roles
            )
        }
    }
}
