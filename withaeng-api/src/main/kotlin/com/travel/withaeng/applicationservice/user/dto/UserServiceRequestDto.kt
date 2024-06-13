package com.travel.withaeng.applicationservice.user.dto

import com.travel.withaeng.domain.user.*

data class AddUserDetailsServerRequest (
    val userId: Long,
    val nickname: String? = null,
    val mbti: UserMbti? = null,
    val preferTravelType: UserPreferTravelType? = null,
    val preferTravelThemes: List<UserPreferTravelTheme>? = null,
    val consumeStyle: UserConsumeStyle? = null,
    val foodRestrictions: List<UserFoodRestriction>? = null,
    val preferAccompanyGender: UserPreferAccompanyGender? = null,
    val smokingType: UserSmokingType? = null,
    val drinkingType: UserDrinkingType? = null
)

fun AddUserDetailsServerRequest.toDomainDto(): AddDetailsUserDto = AddDetailsUserDto(
    nickname = nickname,
    mbti = mbti,
    preferTravelType = preferTravelType,
    preferTravelThemes = preferTravelThemes,
    consumeStyle = consumeStyle,
    foodRestrictions = foodRestrictions,
    preferAccompanyGender = preferAccompanyGender,
    smokingType = smokingType,
    drinkingType = drinkingType
)
