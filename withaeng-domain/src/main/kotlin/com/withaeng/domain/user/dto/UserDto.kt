package com.withaeng.domain.user.dto

import com.withaeng.domain.user.*
import java.time.LocalDate

data class UserSimpleDto(
    val id: Long,
    val email: String?,
    val password: String?,
    val birth: LocalDate?,
    val gender: Gender?,
    val mannerScore: Double,
    val profile: UserProfileDto,
    val roles: Set<UserRole>,
)

fun User.toSimpleDto(): UserSimpleDto = UserSimpleDto(
    id = id,
    email = email,
    password = password,
    birth = birth,
    gender = gender,
    mannerScore = mannerScore,
    profile = UserProfileDto(
        nickname = profile.nickname,
        introduction = profile.introduction,
        profileImageUrl = profile.profileImageUrl,
        instagram = profile.instagram,
    ),
    roles = roles,
)

data class UserDetailDto(
    val id: Long,
    val createdDate: LocalDate,
    val email: String?,
    val password: String?,
    val birth: LocalDate?,
    val gender: Gender?,
    val mannerScore: Double,
    val profile: UserProfileDto,
    val travelPreference: UserTravelPreferenceDto? = null,
    val travelLiking: UserTravelLikingDto? = null,
    val roles: Set<UserRole>,
)

data class UserProfileDto(
    val nickname: String,
    val introduction: String? = null,
    val profileImageUrl: String? = null,
    val instagram: String?,
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

data class UserTravelLikingDto(
    val travelLikings: Set<TravelLiking>? = emptySet(),
)

fun User.toDetailDto(): UserDetailDto = UserDetailDto(
    id = id,
    createdDate = createdAt.toLocalDate(),
    email = email,
    password = password,
    birth = birth,
    gender = gender,
    mannerScore = mannerScore,
    profile = UserProfileDto(
        nickname = profile.nickname,
        introduction = profile.introduction,
        profileImageUrl = profile.profileImageUrl,
        instagram = profile.instagram,
    ),
    travelPreference = travelPreference?.toDto(),
    travelLiking = travelLikings?.toDto(),
    roles = roles,
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

fun MutableSet<UserTravelLiking>.toDto(): UserTravelLikingDto = UserTravelLikingDto(
    travelLikings = map { TravelLiking(it.likingId, it.likingValue) }.toSet(),
)