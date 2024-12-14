package com.withaeng.api.applicationservice.user.dto

import com.withaeng.domain.user.*
import com.withaeng.domain.user.dto.UserDetailDto
import com.withaeng.domain.user.dto.UserSimpleDto
import com.withaeng.domain.user.dto.UserTravelPreferenceDto
import java.time.LocalDate

data class UserSimpleResponse(
    val id: Long,
    val email: String?,
    val nickname: String,
)

fun UserSimpleDto.toSimpleResponse(): UserSimpleResponse = UserSimpleResponse(
    id = id,
    email = email,
    nickname = profile.nickname
)

data class UserTravelPreferenceResponse(
    val mbti: Set<UserMbti>? = emptySet(),
    val preferTravelType: UserPreferTravelType? = null,
    val preferTravelThemes: Set<UserPreferTravelTheme> = emptySet(),
    val consumeStyle: UserConsumeStyle? = null,
    val foodRestrictions: Set<UserFoodRestriction> = emptySet(),
    val smokingType: UserSmokingType? = null,
    val drinkingType: UserDrinkingType? = null,
)

fun UserTravelPreferenceDto.toServiceResponse(): UserTravelPreferenceResponse =
    UserTravelPreferenceResponse(
        mbti = mbti,
        preferTravelType = preferTravelType,
        preferTravelThemes = preferTravelThemes,
        consumeStyle = consumeStyle,
        foodRestrictions = foodRestrictions,
        smokingType = smokingType,
        drinkingType = drinkingType
    )

data class UserStatisticalProfileResponse(
    val id: Long,
    val nickname: String,
    val introduction: String? = null,
    val gender: Gender?,
    val birth: LocalDate?,
    val profileImageUrl: String? = null,
    val profileCompletionPercentage: Int,
    val mannerScore: Double,
    val accompanyCount: Int,
    val createdAt: LocalDate,
) {
    companion object {
        fun of(
            userDetail: UserDetailDto,
            profileCompletionPercentage: Int,
            accompanyCount: Int,
        ): UserStatisticalProfileResponse = UserStatisticalProfileResponse(
            id = userDetail.id,
            nickname = userDetail.profile.nickname,
            introduction = userDetail.profile.introduction,
            gender = userDetail.gender,
            birth = userDetail.birth,
            profileImageUrl = userDetail.profile.profileImageUrl,
            profileCompletionPercentage = profileCompletionPercentage,
            mannerScore = userDetail.mannerScore,
            accompanyCount = accompanyCount,
            createdAt = userDetail.createdDate,
        )
    }
}

data class PutProfileImageResponse(
    val id: Long,
    val preSignedUrl: String? = null,
)
