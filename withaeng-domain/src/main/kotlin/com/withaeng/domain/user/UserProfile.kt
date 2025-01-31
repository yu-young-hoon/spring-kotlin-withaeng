package com.withaeng.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class UserProfile(
    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "introduction", nullable = true)
    var introduction: String? = null,

    @Column(name = "profile_image_url", nullable = true)
    var profileImageUrl: String? = null,

    @Column(name = "instagram", nullable = true)
    var instagram: String? = null,
)
