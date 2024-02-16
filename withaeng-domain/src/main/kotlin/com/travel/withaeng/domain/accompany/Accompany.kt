package com.travel.withaeng.domain.accompany

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "accompany")
@Entity
class Accompany(
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "title", nullable = false)
    val title: String,

    @Lob
    @Column(name = "content", nullable = false)
    val content: String,

    @Embedded
    val destination: Destination,

    @Column(name = "start_trip_date", nullable = false)
    val startTripDate: LocalDate,

    @Column(name = "end_trip_date", nullable = false)
    val endTripDate: LocalDate,

    @Column(name = "banner_image_url")
    val bannerImageUrl: String?,

    @Column(name = "view_counts", nullable = false)
    val viewCounts: Long = 0L
) : BaseEntity()