package com.withaeng.api.applicationservice.auth

import com.withaeng.api.applicationservice.auth.dto.*
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.jwt.JwtAgent
import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.user.Gender
import com.withaeng.domain.user.UserRole
import com.withaeng.domain.user.UserService
import com.withaeng.domain.user.dto.UserSimpleDto
import com.withaeng.domain.verificationemail.VerificationEmailService
import com.withaeng.domain.verificationemail.VerificationEmailType
import com.withaeng.external.client.GoogleClient
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
@Transactional(readOnly = true)
class AuthService(
    private val userService: UserService,
    private val verificationEmailService: VerificationEmailService,
    private val jwtAgent: JwtAgent,
    private val passwordEncoder: PasswordEncoder,
    private val googleClient: GoogleClient,
) {
    @Transactional
    fun resendEmail(request: ResendEmailServiceRequest) {
        val userDto = userService.findByEmailOrNull(request.email) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "이메일에 해당하는 유저를 찾을 수 없습니다.",
        )
        if (userDto.isValidUser()) {
            throw WithaengException.of(
                type = WithaengExceptionType.INVALID_ACCESS,
                message = "이미 인증된 유저입니다.",
            )
        }
        verificationEmailService.deleteAllByUserIdAndEmailType(userDto.id, VerificationEmailType.VERIFY_EMAIL)
        verificationEmailService.create(
            email = userDto.email!!,
            userId = userDto.id,
            code = UUID.randomUUID().toString(),
            type = VerificationEmailType.VERIFY_EMAIL,
            host = request.host,
        )
    }

    @Transactional
    fun verifyEmail(request: VerifyEmailServiceRequest) {
        val requestedEmail = request.email
        val userDto = userService.findByEmailOrNull(requestedEmail)
            ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = "이메일에 해당하는 유저를 찾을 수 없습니다.",
            )
        if (userDto.isValidUser()) {
            throw WithaengException.of(
                type = WithaengExceptionType.INVALID_ACCESS,
                message = "이미 인증된 유저입니다.",
            )
        }
        verifyEmailCode(
            email = requestedEmail,
            userId = userDto.id,
            code = request.code,
        )
        userService.grantUserRole(userDto.id)
    }

    @Transactional
    fun sendEmailForChangingPassword(request: SendEmailForChangePasswordServiceRequest) {
        val userDto = userService.findByEmailOrNull(request.email) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "이메일에 해당하는 유저를 찾을 수 없습니다.",
        )
        verificationEmailService.deleteAllByUserIdAndEmailType(userDto.id, VerificationEmailType.CHANGE_PASSWORD)
        verificationEmailService.create(
            email = userDto.email!!,
            userId = userDto.id,
            code = UUID.randomUUID().toString(),
            type = VerificationEmailType.CHANGE_PASSWORD,
            host = request.host,
        )
    }

    @Transactional
    fun changePassword(request: ChangePasswordServiceRequest) {
        val email = request.email
        val userDto = userService.findByEmailOrNull(email) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "이메일에 해당하는 유저를 찾을 수 없습니다.",
        )
        verifyEmailCode(
            email = email,
            userId = userDto.id,
            code = request.code,
        )
        userService.replacePassword(userDto.id, passwordEncoder.encode(request.password))
    }

    private fun verifyEmailCode(email: String, userId: Long, code: String) {
        val verificationEmailDto = verificationEmailService.findByEmail(email)
        if (verificationEmailDto.userId != userId || verificationEmailDto.code != code) {
            throw WithaengException.of(WithaengExceptionType.INVALID_INPUT, "Code가 올바르지 않습니다.")
        }
        verificationEmailService.deleteById(verificationEmailDto.id)
    }

    private fun UserSimpleDto.isValidUser(): Boolean {
        return roles.any { it == UserRole.USER }
    }

    @Transactional
    fun signInForOAuth(request: SignInForOAuthServiceRequest): UserResponse {
        val token = googleClient.getToken(request.code) ?: throw WithaengException.of(
            type = WithaengExceptionType.INVALID_ACCESS,
            message = "구글 토큰을 받아오는데 실패했습니다.",
        )
        val me = googleClient.getMe(token.accessToken) ?: throw WithaengException.of(
            type = WithaengExceptionType.INVALID_ACCESS,
            message = "구글 사용자 정보를 받아오는데 실패했습니다.",
        )
        userService.findByGoogleId(me.id)?.let {
            return UserResponse(it.id, it.email, jwtAgent.provide(UserInfo.from(it)))
        }

        val info = googleClient.getInfo(token.accessToken)
        val birth = info?.birthdays?.map {
            LocalDate.of(it.date.year, it.date.month, it.date.day)
        }?.firstOrNull()
        val gender = info?.genders?.map {
            Gender.valueOf(it.value.uppercase())
        }?.firstOrNull()
        userService.create(
            request.toCommand(
                temporaryNickname = UserNicknameUtils.createTemporaryNickname(),
                name = me.name,
                email = me.email,
                googleId = me.id,
                birth = birth ?: LocalDate.now(),
                gender = gender ?: Gender.MALE,
            ),
        ).let {
            return UserResponse(
                userId = it.id,
                email = it.email,
                accessToken = jwtAgent.provide(UserInfo.from(it)),
                signUp = true,
            )
        }
    }
}
