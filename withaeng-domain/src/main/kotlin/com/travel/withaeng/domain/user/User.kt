package com.travel.withaeng.domain.user

import com.travel.withaeng.converter.UserRoleConverter
import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "users")
@Entity
class User(
    @Column(name = "nickname", nullable = false)
    val nickname: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    val socialType: SocialType,

    @Column(name = "provider_unique_key", nullable = false)
    val providerUniqueKey: String,

    @Column(name = "birth", nullable = true)
    val birth: LocalDate? = null,

    @Column(name = "is_male", nullable = true)
    val isMale: Boolean? = null,

    @Column(name = "profile_image_url")
    val profileImageUrl: String? = null,

    @Column(name = "bio")
    val bio: String? = null,

    @Convert(converter = UserRoleConverter::class)
    @Column(name = "roles")
    val roles: Set<UserRole>,
) : BaseEntity() {

    companion object {

        fun create(
            nickname: String,
            socialType: SocialType,
            providerUniqueKey: String,
            birth: LocalDate? = null,
            isMale: Boolean? = null,
            profileImageUrl: String? = null,
            bio: String? = null,
        ): User {
            return User(
                nickname = nickname,
                socialType = socialType,
                profileImageUrl = profileImageUrl,
                providerUniqueKey = providerUniqueKey,
                birth = birth,
                isMale = isMale,
                bio = bio,
                roles = setOf(UserRole.USER)
            )
        }
    }
}