package com.travel.withaeng.domain.accompanyreplylike

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "accompany_reply_like")
@Entity
class AccompanyReplyLikeEntity(
    @Column(name = "accompany_reply_id", nullable = false)
    val accompanyReplyId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long
) : BaseEntity()