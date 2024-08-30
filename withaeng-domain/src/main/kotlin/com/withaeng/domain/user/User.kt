package com.withaeng.domain.user

import com.withaeng.domain.BaseEntity
import com.withaeng.domain.converter.UserFoodRestrictionConverter
import com.withaeng.domain.converter.UserPreferTravelThemeConverter
import com.withaeng.domain.converter.UserRoleConverter
import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "users")
@Entity
class User(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "birth", nullable = false)
    val birth: LocalDate,

    @Column(name = "is_male", nullable = false)
    val isMale: Boolean,

    @Column(name = "profile_image_url", nullable = true)
    var profileImageUrl: String? = null,

    @Column(name = "bio", nullable = true)
    var bio: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "mbti", nullable = true)
    var mbti: UserMbti? = null,

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
    @Column(name = "prefer_accompany_gender", nullable = true)
    var preferAccompanyGender: UserPreferAccompanyGender? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "smoking_type", nullable = true)
    var smokingType: UserSmokingType? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "drinking_type", nullable = true)
    var drinkingType: UserDrinkingType? = null,

    @Convert(converter = UserRoleConverter::class)
    @Column(name = "roles")
    var roles: Set<UserRole>,
) : BaseEntity() {

    companion object {

        fun create(
            email: String,
            nickname: String,
            password: String,
            birth: LocalDate,
            isMale: Boolean
        ): User {
            return User(
                email = email,
                nickname = nickname,
                password = password,
                birth = birth,
                isMale = isMale,
                roles = setOf(UserRole.NON_USER)
            )
        }
    }
}
