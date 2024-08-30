package com.withaeng.api.applicationservice.user.dto

import com.withaeng.domain.user.AddDetailsUserDto
import com.withaeng.domain.user.UserConsumeStyle
import com.withaeng.domain.user.UserDrinkingType
import com.withaeng.domain.user.UserFoodRestriction
import com.withaeng.domain.user.UserMbti
import com.withaeng.domain.user.UserPreferAccompanyGender
import com.withaeng.domain.user.UserPreferTravelTheme
import com.withaeng.domain.user.UserPreferTravelType
import com.withaeng.domain.user.UserSmokingType

data class AddUserDetailsServerRequest(
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
