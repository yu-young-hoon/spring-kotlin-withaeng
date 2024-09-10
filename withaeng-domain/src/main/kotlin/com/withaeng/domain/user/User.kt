package com.withaeng.domain.user

import com.withaeng.domain.BaseEntity
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

    @Column(name = "birth", nullable = false)
    val birth: LocalDate,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    val gender: Gender,

    @Embedded
    val profile: UserProfile,

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var travelPreference: UserTravelPreference? = null,

    @Convert(converter = UserRoleConverter::class)
    @Column(name = "roles")
    var roles: Set<UserRole>,
) : BaseEntity() {

    companion object {
        fun create(
            email: String,
            password: String,
            birth: LocalDate,
            gender: Gender,
            nickname: String,
        ): User {
            return User(
                email = email,
                password = password,
                birth = birth,
                gender = gender,
                profile = UserProfile(
                    nickname = nickname
                ),
                roles = setOf(UserRole.NON_USER)
            )
        }
    }
}
