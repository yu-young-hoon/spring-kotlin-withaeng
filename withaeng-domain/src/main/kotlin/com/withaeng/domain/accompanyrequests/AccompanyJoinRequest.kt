package com.withaeng.domain.accompanyrequests

import com.withaeng.domain.BaseEntity
import com.withaeng.domain.accompany.Accompany
import jakarta.persistence.*

@Entity
@Table(name = "accompany_join_request")
class AccompanyJoinRequest(

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Enumerated(EnumType.STRING)
    var status: AccompanyJoinRequestStatus,

    @ManyToOne
    @JoinColumn(name = "accompany_id")
    val accompany: Accompany,
) : BaseEntity() {

    companion object {
        fun create(userId: Long, accompany: Accompany): AccompanyJoinRequest {
            return AccompanyJoinRequest(
                userId = userId,
                status = AccompanyJoinRequestStatus.WAIT,
                accompany = accompany,
            )
        }
    }

    fun cancel() {
        status = AccompanyJoinRequestStatus.CANCEL
    }

    fun accept() {
        status = AccompanyJoinRequestStatus.ACCEPT
    }

    fun reject() {
        status = AccompanyJoinRequestStatus.REJECT
    }

    fun isNotWaiting() =
        this.status != AccompanyJoinRequestStatus.WAIT
}