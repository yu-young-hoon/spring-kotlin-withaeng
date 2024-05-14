package com.travel.withaeng.domain.accompany

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
data class AccompanyDestination(
    @Enumerated(EnumType.STRING)
    @Column(name = "continent")
    val continent: Continent,

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    val country: Country,

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    val city: City
)