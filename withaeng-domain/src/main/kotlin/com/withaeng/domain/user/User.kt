package com.withaeng.domain.user

import com.withaeng.domain.BaseEntity
import com.withaeng.domain.converter.UserRoleConverter
import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "users")
@Entity
class User(
    @Column(name = "email", nullable = true, unique = true)
    val email: String? = null,

    @Column(name = "password", nullable = true)
    var password: String? = null,

    @Column(name = "google_id", nullable = true)
    val googleId: String? = null,

    @Column(name = "birth", nullable = true)
    val birth: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = true)
    val gender: Gender? = null,

    @Column(name = "manner_score", nullable = false)
    var mannerScore: Double = 36.5,

    @Embedded
    val profile: UserProfile,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var travelLikings: MutableSet<UserTravelLiking> = mutableSetOf(),

    @Convert(converter = UserRoleConverter::class)
    @Column(name = "roles")
    var roles: Set<UserRole>,
) : BaseEntity() {

    companion object {
        fun create(
            email: String?,
            password: String?,
            googleId: String?,
            birth: LocalDate?,
            gender: Gender?,
            nickname: String,
            name: String,
        ): User {
            return User(
                email = email,
                password = password,
                googleId = googleId,
                birth = birth,
                gender = gender,
                profile = UserProfile(
                    nickname = nickname,
                    name = name,
                ),
                roles = setOf(UserRole.USER),
            )
        }
    }
}
