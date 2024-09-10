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

data class UpdateProfileCommand(
    val nickname: String? = null,
    val introduction: String? = null,
    val profileImageUrl: String? = null,
)

data class UpdateTravelPreferenceCommand(
    val mbti: Set<UserMbti>? = emptySet(),
    val preferTravelType: UserPreferTravelType? = null,
    val preferTravelThemes: Set<UserPreferTravelTheme>? = emptySet(),
    val consumeStyle: UserConsumeStyle? = null,
    val foodRestrictions: Set<UserFoodRestriction>? = emptySet(),
    val smokingType: UserSmokingType? = null,
    val drinkingType: UserDrinkingType? = null,
)