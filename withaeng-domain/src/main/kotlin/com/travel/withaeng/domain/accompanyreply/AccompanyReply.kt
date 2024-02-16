package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "accompany_reply")
@Entity
class AccompanyReply(
    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "content", nullable = false)
    val content: String,

    @Column(name = "depth", nullable = false)
    val depth: Long,

    @Column(name = "group_id", nullable = false)
    val groupId: Long
) : BaseEntity()