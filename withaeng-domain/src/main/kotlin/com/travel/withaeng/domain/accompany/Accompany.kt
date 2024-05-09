package com.travel.withaeng.domain.accompany

import com.travel.withaeng.converter.TagIdsConverter
import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate

@DynamicUpdate
@Table(name = "accompany")
@Entity
class Accompany(

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "title", nullable = false)
    var title: String,

    @Lob
    @Column(name = "content", nullable = false)
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "accompany_status", nullable = false)
    var accompanyStatus: AccompanyStatus = AccompanyStatus.ING,

    @Column(name = "start_trip_date", nullable = false)
    var startTripDate: LocalDate,

    @Column(name = "end_trip_date", nullable = false)
    var endTripDate: LocalDate,

    @Column(name = "banner_image_url")
    var bannerImageUrl: String?,

    @Column(name = "member_count", nullable = false)
    var memberCount: Long = 0L,

    @Column(name = "view_count", nullable = false)
    var viewCount: Long = 0L,

    @Column(name = "open_kakao_url", nullable = false)
    var openKakaoUrl: String,

    @Embedded
    var accompanyDestination: AccompanyDestination,

    @Convert(converter = TagIdsConverter::class)
    @Column(name = "tag_ids", nullable = false)
    var tagIds: Set<Long> = setOf()

) : BaseEntity() {

    companion object {

        fun create(params: CreateAccompanyDto): Accompany {
            return Accompany(
                userId = params.userId,
                title = params.title,
                content = params.content,
                startTripDate = params.startTripDate,
                endTripDate = params.endTripDate,
                bannerImageUrl = params.bannerImageUrl,
                memberCount = params.memberCount,
                openKakaoUrl = params.openKakaoUrl,
                accompanyDestination = params.destination,
                tagIds = params.tagIds
            )
        }

    }
}