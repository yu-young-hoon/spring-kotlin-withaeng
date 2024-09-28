package com.withaeng.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class UserProfile(
    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "introduction", nullable = true)
    var introduction: String? = null,

    @Column(name = "profile_image_url", nullable = true)
    var profileImageUrl: String? = null,
)
