package com.travel.withaeng.domain.accompany

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.hibernate.annotations.Comment

@Embeddable
data class AccompanyDestination(
    @Enumerated(EnumType.STRING)
    @Column(name = "continent")
    @Comment("대륙")
    val continent: Continent,

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    @Comment("국가")
    val country: Country,

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    @Comment("도시")
    val city: City
)