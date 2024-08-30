package com.withaeng.domain.accompanystatistics

import com.withaeng.domain.accompany.Accompany
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "accompany_statistics")
class AccompanyStatistics(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0L,

    @OneToOne
    @JoinColumn(name = "accompany_id", nullable = false)
    val accompany: Accompany,

    @Column(name = "view_count", nullable = false)
    @Comment("조회수")
    var viewCount: Long = 0L,
) {

    fun increaseViewCount() {
        this.viewCount++
    }

}