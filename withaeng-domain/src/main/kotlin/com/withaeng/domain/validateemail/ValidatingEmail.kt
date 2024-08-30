package com.withaeng.domain.validateemail

import com.withaeng.domain.BaseEntity
import jakarta.persistence.*

@Table(name = "validating_emails")
@Entity
class ValidatingEmail(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "code", nullable = false)
    val code: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: ValidatingEmailType = ValidatingEmailType.VALIDATE_EMAIL,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: ValidatingEmailStatus = ValidatingEmailStatus.YET,
) : BaseEntity()
