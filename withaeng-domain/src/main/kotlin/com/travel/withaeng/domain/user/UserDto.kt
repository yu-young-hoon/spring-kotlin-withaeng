package com.travel.withaeng.domain.user

import java.time.LocalDate

data class UserDto(
    val id: Long,
    val nickname: String,
    val socialType: SocialType,
    val providerUniqueKey: String,
    val birth: LocalDate? = null,
    val isMale: Boolean? = null,
    val profileImageUrl: String? = null,
    val bio: String? = null,
    val roles: Set<UserRole>
)

fun User.toDto(): UserDto = UserDto(
    id = id,
    nickname = nickname,
    socialType = socialType,
    providerUniqueKey = providerUniqueKey,
    birth = birth,
    isMale = isMale,
    profileImageUrl = profileImageUrl,
    bio = bio,
    roles = roles
)

data class CreateUserDto(
    val nickname: String,
    val socialType: SocialType,
    val providerUniqueKey: String,
    val birth: LocalDate? = null,
    val isMale: Boolean? = null,
    val profileImageUrl: String? = null,
    val bio: String? = null,
)