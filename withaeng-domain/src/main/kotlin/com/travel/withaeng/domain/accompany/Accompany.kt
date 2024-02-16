package com.travel.withaeng.domain.accompany

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

data class Accompany(
    val userId: Long,
    val title: String,
    @Lob
    val content: String,
    val destination: Destination,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String?,
    val viewCounts: Long,
    val createdAt: LocalDateTime
)

fun AccompanyEntity.toDto(): Accompany = Accompany(
    userId = userId,
    title = title,
    content = content,
    destination = destination,
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    viewCounts = viewCounts,
    createdAt = createdAt
)