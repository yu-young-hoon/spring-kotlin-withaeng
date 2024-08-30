package com.withaeng.domain.accompanyreplylike

import com.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "accompany_reply_like")
@Entity
class AccompanyReplyLike(

    @Column(name = "reply_id", nullable = false)
    val replyId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long

) : BaseEntity()