package com.travel.withaeng.domain.accompany

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Destination(
    @Column(name = "continent", nullable = false)
    val continent: String,

    @Column(name = "country", nullable = false)
    val country: String,

    @Column(name = "city", nullable = false)
    val city: String
)