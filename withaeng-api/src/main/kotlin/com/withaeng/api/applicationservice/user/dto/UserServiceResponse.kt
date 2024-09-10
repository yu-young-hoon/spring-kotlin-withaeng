package com.withaeng.api.applicationservice.user.dto

import com.withaeng.domain.user.*
import com.withaeng.domain.user.dto.UserDetailsDto
import com.withaeng.domain.user.dto.UserSimpleDto
import java.time.LocalDate

data class UserSimpleServiceResponse(
    val id: Long,
    val email: String,
    val nickname: String,
)

fun UserSimpleDto.toSimpleResponse(): UserSimpleServiceResponse = UserSimpleServiceResponse(
    id = id,
    email = email,
    nickname = profile.nickname
)

data class UserDetailServiceResponse(
    val id: Long,
    val email: String,
    val gender: Gender,
    val birth: LocalDate,
    val profile: UserProfileServiceResponse,
    val travelPreference: UserTravelPreferenceServiceResponse? = null,
)

data class UserProfileServiceResponse(
    val nickname: String,
    val introduction: String? = null,
    val profileImageUrl: String? = null,
)

data class UserTravelPreferenceServiceResponse(
    val mbti: Set<UserMbti>? = emptySet(),
    val preferTravelType: UserPreferTravelType? = null,
    val preferTravelThemes: Set<UserPreferTravelTheme> = emptySet(),
    val consumeStyle: UserConsumeStyle? = null,
    val foodRestrictions: Set<UserFoodRestriction> = emptySet(),
    val smokingType: UserSmokingType? = null,
    val drinkingType: UserDrinkingType? = null,
)

fun UserDetailsDto.toDetailResponse(): UserDetailServiceResponse = UserDetailServiceResponse(
    id = id,
    email = email,
    gender = gender,
    birth = birth,
    profile = UserProfileServiceResponse(
        nickname = profile.nickname,
        introduction = profile.introduction,
        profileImageUrl = profile.profileImageUrl,
    ),
    travelPreference = travelPreference?.let {
        UserTravelPreferenceServiceResponse(
            mbti = it.mbti,
            preferTravelType = it.preferTravelType,
            preferTravelThemes = it.preferTravelThemes,
            consumeStyle = it.consumeStyle,
            foodRestrictions = it.foodRestrictions,
            smokingType = it.smokingType,
            drinkingType = it.drinkingType
        )
    },
)
