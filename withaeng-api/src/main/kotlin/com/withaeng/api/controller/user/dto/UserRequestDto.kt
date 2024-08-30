package com.withaeng.api.controller.user.dto

import com.withaeng.domain.user.*
import com.withaeng.api.applicationservice.user.dto.AddUserDetailsServerRequest
import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.validator.constraints.Length

@Schema(description = "[Request] User 정보를 추가합니다")
data class AddUserDetailsRequest(
    @Schema(description = "유저 닉네임")
    @field:Length(min = 2, max = 10, message = "닉네임은 2 ~ 10자리 문자열로 입력해야 합니다.")
    val nickname: String? = null,

    @Schema(description = "유저 MBTI")
    val mbti: UserMbti? = null,

    @Schema(description = "여행 선호 지역 (국내 / 해외)")
    val preferTravelType: UserPreferTravelType? = null,

    @Schema(description = "여행 관심사 (사진, 음식 등)")
    val preferTravelThemes: List<UserPreferTravelTheme>? = null,

    @Schema(description = "여행 소비 스타일 (가성비, 쓸 때 쓰는 타입 등)")
    val consumeStyle: UserConsumeStyle? = null,

    @Schema(description = "못 먹는 음식 (갑각류, 해산물 등)")
    val foodRestrictions: List<UserFoodRestriction>? = null,

    @Schema(description = "원하는 동행자의 성별")
    val preferAccompanyGender: UserPreferAccompanyGender? = null,

    @Schema(description = "흡연 타입")
    val smokingType: UserSmokingType? = null,

    @Schema(description = "음주 타입")
    val drinkingType: UserDrinkingType? = null
)

fun AddUserDetailsRequest.toServiceRequest(userId: Long): AddUserDetailsServerRequest = AddUserDetailsServerRequest(
    userId = userId,
    nickname = nickname,
    mbti = mbti,
    preferTravelType = preferTravelType,
    preferTravelThemes = preferTravelThemes,
    consumeStyle = consumeStyle,
    foodRestrictions = foodRestrictions,
    preferAccompanyGender = preferAccompanyGender,
    smokingType = smokingType
)
