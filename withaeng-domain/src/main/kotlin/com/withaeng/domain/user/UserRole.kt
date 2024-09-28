package com.withaeng.domain.user

enum class UserRole(
    val role: String
) {
    NON_USER("ROLE_NON_USER"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    fun getActualRoleName(): String {
        return role.split("_")[1]
    }
}