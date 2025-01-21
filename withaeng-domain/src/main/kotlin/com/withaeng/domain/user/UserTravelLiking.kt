package com.withaeng.domain.user

import com.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Table(name = "user_travel_liking")
@Entity
class UserTravelLiking(
    @Column(name = "liking_id", nullable = false)
    var likingId: Int,

    @Column(name = "drinking_type", nullable = false)
    var likingValue: Int,

    @ManyToOne
    val user: User,
) : BaseEntity() {
    companion object {
        fun create(user: User, likingId: Int, likingValue: Int): UserTravelLiking {
            return UserTravelLiking(user = user, likingId = likingId, likingValue = likingValue)
        }
    }
}