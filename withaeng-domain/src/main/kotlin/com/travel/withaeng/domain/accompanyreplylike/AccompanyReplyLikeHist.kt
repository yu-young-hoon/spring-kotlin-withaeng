package com.travel.withaeng.domain.accompanyreplylike

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "accompany_reply_like_hist")
@Entity
class AccompanyReplyLikeHist(

    @Column(name = "reply_like_id", nullable = false)
    val replyLikeId: Long,

    @Column(name = "reply_id", nullable = false)
    val replyId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "exec_cd", nullable = false)
    val execCd: String

) : BaseEntity()