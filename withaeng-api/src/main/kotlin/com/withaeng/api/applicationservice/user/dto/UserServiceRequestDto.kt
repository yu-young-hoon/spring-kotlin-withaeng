package com.withaeng.api.applicationservice.user.dto

import com.withaeng.domain.user.*
import com.withaeng.domain.user.dto.UpdateTravelPreferenceCommand

data class UpdateTravelPreferenceServiceRequest(
    val userId: Long,
    val nickname: String? = null,
    val mbti: List<UserMbti>? = null,
    val preferTravelType: UserPreferTravelType? = null,
    val preferTravelThemes: List<UserPreferTravelTheme>? = null,
    val consumeStyle: UserConsumeStyle? = null,
    val foodRestrictions: List<UserFoodRestriction>? = null,
    val smokingType: UserSmokingType? = null,
    val drinkingType: UserDrinkingType? = null,
)

fun UpdateTravelPreferenceServiceRequest.toCommand(): UpdateTravelPreferenceCommand =
    UpdateTravelPreferenceCommand(
        nickname = nickname,
        mbti = mbti,
        preferTravelType = preferTravelType,
        preferTravelThemes = preferTravelThemes,
        consumeStyle = consumeStyle,
        foodRestrictions = foodRestrictions,
        smokingType = smokingType,
        drinkingType = drinkingType
    )
