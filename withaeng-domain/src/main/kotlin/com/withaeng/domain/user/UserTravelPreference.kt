package com.withaeng.domain.user

import com.withaeng.domain.BaseEntity
import com.withaeng.domain.converter.UserFoodRestrictionConverter
import com.withaeng.domain.converter.UserMbtiConverter
import com.withaeng.domain.converter.UserPreferTravelThemeConverter
import jakarta.persistence.*

@Table(name = "user_travel_preference")
@Entity
class UserTravelPreference(
    @Convert(converter = UserMbtiConverter::class)
    @Column(name = "mbti", nullable = true)
    var mbti: Set<UserMbti> = emptySet(),

    @Enumerated(EnumType.STRING)
    @Column(name = "prefer_travel_type", nullable = true)
    var preferTravelType: UserPreferTravelType? = null,

    @Convert(converter = UserPreferTravelThemeConverter::class)
    @Column(name = "prefer_travel_theme", nullable = false)
    var preferTravelThemes: Set<UserPreferTravelTheme> = emptySet(),

    @Enumerated(EnumType.STRING)
    @Column(name = "consume_style", nullable = true)
    var consumeStyle: UserConsumeStyle? = null,

    @Convert(converter = UserFoodRestrictionConverter::class)
    @Column(name = "food_restriction", nullable = false)
    var foodRestrictions: Set<UserFoodRestriction> = emptySet(),

    @Enumerated(EnumType.STRING)
    @Column(name = "smoking_type", nullable = true)
    var smokingType: UserSmokingType? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "drinking_type", nullable = true)
    var drinkingType: UserDrinkingType? = null,

    @OneToOne
    val user: User,
) : BaseEntity()