package com.travel.withaeng.domain.user

import com.travel.withaeng.converter.UserRoleConverter
import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Table
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