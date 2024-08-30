package com.withaeng.api.applicationservice.auth

import java.util.UUID

object UserNicknameUtils {

    private const val TEMPORARY_NICKNAME_PREFIX = "user-"
    private const val TEMPORARY_NICKNAME_SUFFIX_COUNT = 10

    fun createTemporaryNickname(): String {
        val suffix = UUID.randomUUID().toString().replace("-", "").take(TEMPORARY_NICKNAME_SUFFIX_COUNT)
        return TEMPORARY_NICKNAME_PREFIX + suffix
    }
}
