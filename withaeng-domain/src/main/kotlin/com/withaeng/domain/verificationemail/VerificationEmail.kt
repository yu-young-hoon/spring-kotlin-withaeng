package com.withaeng.domain.verificationemail

import com.withaeng.domain.BaseEntity
import jakarta.persistence.*

@Table(name = "verification_email")
@Entity
class VerificationEmail(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "code", nullable = false)
    val code: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: VerificationEmailType,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: VerificationEmailStatus = VerificationEmailStatus.YET,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "host", nullable = true)
    val host: String?,
) : BaseEntity()
