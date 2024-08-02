package com.travel.withaeng.domain.user

import java.time.LocalDate

data class UserSimpleDto(
    val id: Long,
    val email: String,
    val password: String,
    val nickname: String,
    val birth: LocalDate,
    val isMale: Boolean,
    val profileImageUrl: String? = null,
    val bio: String? = null,
    val roles: Set<UserRole>
)

data class UserDetailsDto(
    val id: Long,
    val email: String,
    val password: String,
    val nickname: String,
    val birth: LocalDate,
    val isMale: Boolean,
    val profileImageUrl: String? = null,
    val bio: String? = null,
    val mbti: UserMbti? = null,
    val preferTravelType: UserPreferTravelType? = null,
    val preferTravelThemes: Set<UserPreferTravelTheme> = emptySet(),
    val consumeStyle: UserConsumeStyle? = null,
    val foodRestrictions: Set<UserFoodRestriction> = emptySet(),
    val preferAccompanyGender: UserPreferAccompanyGender? = null,
    val smokingType: UserSmokingType? = null,
    val drinkingType: UserDrinkingType? = null,
    val roles: Set<UserRole>
)

fun User.toSimpleDto(): UserSimpleDto = UserSimpleDto(
    id = id,
    email = email,
    password = password,
    nickname = nickname,
    birth = birth,
    isMale = isMale,
    profileImageUrl = profileImageUrl,
    bio = bio,
    roles = roles
)

fun User.toDetailsDto(): UserDetailsDto = UserDetailsDto(
    id = id,
    email = email,
    password = password,
    nickname = nickname,
    birth = birth,
    isMale = isMale,
    profileImageUrl = profileImageUrl,
    bio = bio,
    mbti = mbti,
    preferTravelType = preferTravelType,
    preferTravelThemes = preferTravelThemes,
    consumeStyle = consumeStyle,
    foodRestrictions = foodRestrictions,
    preferAccompanyGender = preferAccompanyGender,
    smokingType = smokingType,
    drinkingType = drinkingType,
    roles = roles
)

data class CreateUserDto(
    val email: String,
    val nickname: String,
    val password: String,
    val birth: LocalDate,
    val isMale: Boolean
)

data class AddDetailsUserDto(
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
