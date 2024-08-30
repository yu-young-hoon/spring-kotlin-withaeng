package com.withaeng.api.applicationservice.user.dto

import com.withaeng.domain.user.UserConsumeStyle
import com.withaeng.domain.user.UserDetailsDto
import com.withaeng.domain.user.UserDrinkingType
import com.withaeng.domain.user.UserFoodRestriction
import com.withaeng.domain.user.UserMbti
import com.withaeng.domain.user.UserPreferAccompanyGender
import com.withaeng.domain.user.UserPreferTravelTheme
import com.withaeng.domain.user.UserPreferTravelType
import com.withaeng.domain.user.UserSimpleDto
import com.withaeng.domain.user.UserSmokingType
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
