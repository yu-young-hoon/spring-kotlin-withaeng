package com.withaeng.domain.user.dto

import com.withaeng.domain.user.*
import java.time.LocalDate

data class CreateUserCommand(
    val email: String,
    val password: String,
    val birth: LocalDate,
    val gender: Gender,
    val nickname: String,
)

data class UpdateTravelPreferenceCommand(
    val nickname: String? = null,
    val mbti: List<UserMbti>? = null,
    val preferTravelType: UserPreferTravelType? = null,
    val preferTravelThemes: List<UserPreferTravelTheme>? = null,
    val consumeStyle: UserConsumeStyle? = null,
    val foodRestrictions: List<UserFoodRestriction>? = null,
    val smokingType: UserSmokingType? = null,
    val drinkingType: UserDrinkingType? = null,
)