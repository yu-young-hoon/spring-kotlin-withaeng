package com.travel.withaeng.applicationservice.user.dto

import com.travel.withaeng.domain.user.UserConsumeStyle
import com.travel.withaeng.domain.user.UserDetailsDto
import com.travel.withaeng.domain.user.UserDrinkingType
import com.travel.withaeng.domain.user.UserFoodRestriction
import com.travel.withaeng.domain.user.UserMbti
import com.travel.withaeng.domain.user.UserPreferAccompanyGender
import com.travel.withaeng.domain.user.UserPreferTravelTheme
import com.travel.withaeng.domain.user.UserPreferTravelType
import com.travel.withaeng.domain.user.UserSimpleDto
import com.travel.withaeng.domain.user.UserSmokingType
import java.time.LocalDate

data class UserDetailsResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val isMale: Boolean,
    val birth: LocalDate,
    val mbti: UserMbti? = null,
    val preferTravelType: UserPreferTravelType? = null,
    val preferTravelThemes: Set<UserPreferTravelTheme> = emptySet(),
    val consumeStyle: UserConsumeStyle? = null,
    val foodRestrictions: Set<UserFoodRestriction> = emptySet(),
    val preferAccompanyGender: UserPreferAccompanyGender? = null,
    val smokingType: UserSmokingType? = null,
    val drinkingType: UserDrinkingType? = null
) {
    companion object {
        fun from(userDetailsDto: UserDetailsDto): UserDetailsResponse {
            return UserDetailsResponse(
                id = userDetailsDto.id,
                email = userDetailsDto.email,
                nickname = userDetailsDto.nickname,
                isMale = userDetailsDto.isMale,
                birth = userDetailsDto.birth,
                mbti = userDetailsDto.mbti,
                preferTravelType = userDetailsDto.preferTravelType,
                preferTravelThemes = userDetailsDto.preferTravelThemes,
                consumeStyle = userDetailsDto.consumeStyle,
                foodRestrictions = userDetailsDto.foodRestrictions,
                preferAccompanyGender = userDetailsDto.preferAccompanyGender,
                smokingType = userDetailsDto.smokingType,
                drinkingType = userDetailsDto.drinkingType
            )
        }
    }
}

data class UserSimpleResponse(
    val id: Long,
    val email: String,
    val nickname: String
)

fun UserSimpleDto.toSimpleResponse(): UserSimpleResponse = UserSimpleResponse(
    id = id,
    email = email,
    nickname = nickname
)
