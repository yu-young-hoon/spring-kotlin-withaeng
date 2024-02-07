package com.travel.withaeng.domain.accompany

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "accompany")
@Entity
class AccompanyEntity(
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "title", nullable = false)
    val title: String,

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