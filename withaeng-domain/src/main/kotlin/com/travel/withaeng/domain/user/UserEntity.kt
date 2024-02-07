package com.travel.withaeng.domain.user

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "users")
@Entity
class UserEntity(
    @Column(name = "nickname", nullable = false)
    val nickname: String,

    @Column(name = "birth", nullable = false)
    val birth: LocalDate,

    @Column(name = "is_male", nullable = false)
    val isMale: Boolean,

    @Column(name = "profile_image_url")
    val profileImageUrl: String? = null,

    @Column(name = "bio")
    val bio: String? = null
) : BaseEntity()