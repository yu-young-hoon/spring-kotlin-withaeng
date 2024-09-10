package com.withaeng.domain.user.dto

import com.withaeng.domain.user.*
import java.time.LocalDate

data class UserSimpleDto(
    val id: Long,
    val email: String,
    val password: String,
    val birth: LocalDate,
    val gender: Gender,
    val profile: UserProfileDto,
    val roles: Set<UserRole>,
)

fun User.toSimpleDto(): UserSimpleDto = UserSimpleDto(
    id = id,
    email = email,
    password = password,
    birth = birth,
    gender = gender,
    profile = UserProfileDto(
        nickname = profile.nickname,
        introduction = profile.introduction,
        profileImageUrl = profile.profileImageUrl,
    ),
    roles = roles,
)

data class UserDetailsDto(
    val id: Long,
    val email: String,
    val password: String,
    val birth: LocalDate,
    val gender: Gender,
    val profile: UserProfileDto,
    val travelPreference: UserTravelPreferenceDto? = null,
    val roles: Set<UserRole>,
)

data class UserProfileDto(
    val nickname: String,
    val introduction: String? = null,
    val profileImageUrl: String? = null,
)

data class UserTravelPreferenceDto(
    val mbti: Set<UserMbti>? = emptySet(),
    val preferTravelType: UserPreferTravelType? = null,
    val preferTravelThemes: Set<UserPreferTravelTheme> = emptySet(),
    val consumeStyle: UserConsumeStyle? = null,
    val foodRestrictions: Set<UserFoodRestriction> = emptySet(),
    val smokingType: UserSmokingType? = null,
    val drinkingType: UserDrinkingType? = null,
)

fun User.toDetailsDto(): UserDetailsDto = UserDetailsDto(
    id = id,
    email = email,
    password = password,
    birth = birth,
    gender = gender,
    profile = UserProfileDto(
        nickname = profile.nickname,
        introduction = profile.introduction,
        profileImageUrl = profile.profileImageUrl,
    ),
    travelPreference = travelPreference?.toDto(),
    roles = roles
)

fun UserTravelPreference.toDto(): UserTravelPreferenceDto = UserTravelPreferenceDto(
    mbti = mbti,
    preferTravelType = preferTravelType,
    preferTravelThemes = preferTravelThemes,
    consumeStyle = consumeStyle,
    foodRestrictions = foodRestrictions,
    smokingType = smokingType,
    drinkingType = drinkingType,
)
