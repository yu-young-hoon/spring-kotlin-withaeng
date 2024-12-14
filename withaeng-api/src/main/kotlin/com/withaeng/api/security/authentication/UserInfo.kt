package com.withaeng.api.security.authentication

import com.withaeng.domain.user.UserRole
import com.withaeng.domain.user.dto.UserSimpleDto

data class UserInfo(
    val id: Long,
    val email: String?,
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
