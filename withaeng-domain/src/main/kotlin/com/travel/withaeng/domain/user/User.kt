package com.travel.withaeng.domain.user

import com.travel.withaeng.converter.UserFoodRestrictionConverter
import com.travel.withaeng.converter.UserPreferTravelThemeConverter
import com.travel.withaeng.converter.UserRoleConverter
import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "users")
@Entity
class User(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "nickname", nullable = true)
    val nickname: String? = null,

    @Column(name = "birth", nullable = false)
    val birth: LocalDate,

    @Column(name = "is_male", nullable = false)
    val isMale: Boolean,

    @Column(name = "profile_image_url", nullable = true)
    val profileImageUrl: String? = null,

    @Column(name = "bio", nullable = true)
    val bio: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "mbti", nullable = true)
    val mbti: UserMbti? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "prefer_travel_type", nullable = true)
    val preferTravelType: UserPreferTravelType? = null,

    @Convert(converter = UserPreferTravelThemeConverter::class)
    @Column(name = "prefer_travel_theme", nullable = false)
    val preferTravelThemes: List<UserPreferTravelTheme> = emptyList(),

    @Enumerated(EnumType.STRING)
    @Column(name = "consume_style", nullable = true)
    val consumeStyle: UserConsumeStyle? = null,

    @Convert(converter = UserFoodRestrictionConverter::class)
    @Column(name = "food_restriction", nullable = false)
    val foodRestrictions: List<UserFoodRestriction> = emptyList(),

    @Enumerated(EnumType.STRING)
    @Column(name = "prefer_accompany_gender", nullable = true)
    val preferAccompanyGender: UserPreferAccompanyGender? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "smoking_type", nullable = true)
    val smockingType: UserSmokingType? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "drinking_type", nullable = true)
    val drinkingType: UserDrinkingType? = null,

    @Convert(converter = UserRoleConverter::class)
    @Column(name = "roles")
    var roles: Set<UserRole>,
) : BaseEntity() {

    companion object {

        fun create(
            email: String,
            password: String,
            birth: LocalDate,
            isMale: Boolean
        ): User {
            return User(
                email = email,
                password = password,
                birth = birth,
                isMale = isMale,
                roles = setOf(UserRole.NON_USER)
            )
        }
    }
}