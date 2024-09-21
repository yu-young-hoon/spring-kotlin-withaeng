package com.withaeng.api.controller.user.dto

import com.withaeng.api.applicationservice.user.dto.UpdateProfileServiceRequest
import com.withaeng.api.applicationservice.user.dto.UpdateTravelPreferenceServiceRequest
import com.withaeng.domain.user.*
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "[Request] User 프로필 정보를 추가합니다")
data class UpdateProfileRequest(
    @Schema(description = "유저 닉네임")
    val nickname: String? = null,

    @Schema(description = "유저 소개")
    val introduction: String? = null,

    @Schema(description = "프로필 이미지 URL")
    val hasImage: Boolean = false,
)

fun UpdateProfileRequest.toServiceRequest(): UpdateProfileServiceRequest =
    UpdateProfileServiceRequest(
        nickname = nickname,
        introduction = introduction,
        hasImage = hasImage,
    )

@Schema(description = "[Request] User 여행 선호 스타일 정보를 추가합니다")
data class UpdateTravelPreferenceRequest(
    @Schema(description = "유저 MBTI")
    val mbti: Set<UserMbti>? = emptySet(),

    @Schema(description = "여행 선호 지역 (국내 / 해외)")
    val preferTravelType: UserPreferTravelType? = null,

    @Schema(description = "여행 관심사 (사진, 음식 등)")
    val preferTravelThemes: Set<UserPreferTravelTheme>? = emptySet(),

    @Schema(description = "여행 소비 스타일 (가성비, 쓸 때 쓰는 타입 등)")
    val consumeStyle: UserConsumeStyle? = null,

    @Schema(description = "못 먹는 음식 (갑각류, 해산물 등)")
    val foodRestrictions: Set<UserFoodRestriction>? = emptySet(),

    @Schema(description = "흡연 타입")
    val smokingType: UserSmokingType? = null,

    @Schema(description = "음주 타입")
    val drinkingType: UserDrinkingType? = null,
)

fun UpdateTravelPreferenceRequest.toServiceRequest(): UpdateTravelPreferenceServiceRequest =
    UpdateTravelPreferenceServiceRequest(
        mbti = mbti,
        preferTravelType = preferTravelType,
        preferTravelThemes = preferTravelThemes,
        consumeStyle = consumeStyle,
        foodRestrictions = foodRestrictions,
        smokingType = smokingType,
        drinkingType = drinkingType
    )
