package com.travel.withaeng.applicationservice.auth

import com.travel.withaeng.applicationservice.auth.dto.*
import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.domain.user.CreateUserDto
import com.travel.withaeng.domain.user.UserRole
import com.travel.withaeng.domain.user.UserService
import com.travel.withaeng.domain.user.UserSimpleDto
import com.travel.withaeng.domain.validateemail.ValidatingEmailService
import com.travel.withaeng.security.authentication.UserInfo
import com.travel.withaeng.security.jwt.JwtAgent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class AuthApplicationService(
    private val userService: UserService,
    private val validatingEmailService: ValidatingEmailService,
    private val jwtAgent: JwtAgent,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun signUp(request: SignUpServiceRequest): UserResponse {
        val userEmail = request.email
        val userDto = userService.findByEmailOrNull(request.email)
        if (userDto != null) {
            if (userDto.isValidUser()) {
                throw WithaengException.of(
                    type = WithaengExceptionType.ALREADY_EXIST,
                    message = "이미 가입된 이메일입니다."
                )
            }
            userService.deleteByEmail(userEmail)
            validatingEmailService.deleteAllByUserId(userDto.id)
        }
        val newUserDto = userService.create(request.toCreateUserDto())
        validatingEmailService.create(newUserDto.email, newUserDto.id, UUID.randomUUID().toString())
        return UserResponse(newUserDto.id, newUserDto.email, jwtAgent.provide(UserInfo.from(newUserDto)))
    }

    @Transactional
    fun signIn(request: SignInServiceRequest): UserResponse {
        val userDto = userService.findByEmailOrNull(request.email)
            ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = "이메일에 해당하는 유저를 찾을 수 없습니다."
            )
        checkValidUserPassword(request.password, userDto.password)
        return UserResponse(userDto.id, userDto.email, jwtAgent.provide(UserInfo.from(userDto)))
    }

    @Transactional
    fun validateEmail(request: ValidateEmailServiceRequest) {
        val requestedEmail = request.email
        val userDto = userService.findByEmailOrNull(requestedEmail)
            ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = "이메일에 해당하는 유저를 찾을 수 없습니다."
            )
        if (userDto.isValidUser()) {
            throw WithaengException.of(
                type = WithaengExceptionType.INVALID_ACCESS,
                message = "이미 인증된 유저입니다."
            )
        }
        validateEmailCode(
            email = requestedEmail,
            userId = userDto.id,
            code = request.code
        )
        userService.grantUserRole(userDto.id)
    }

    @Transactional
    fun sendEmailForChangeEmail(request: SendEmailForChangePasswordServiceRequest) {
        val userDto = userService.findByEmailOrNull(request.email) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "이메일에 해당하는 유저를 찾을 수 없습니다."
        )
        validatingEmailService.create(userDto.email, userDto.id, UUID.randomUUID().toString())
    }

    @Transactional
    fun changePassword(request: ChangePasswordServiceRequest) {
        val email = request.email
        val userDto = userService.findByEmailOrNull(email) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "이메일에 해당하는 유저를 찾을 수 없습니다."
        )
        validateEmailCode(
            email = email,
            userId = userDto.id,
            code = request.code
        )
        userService.updatePassword(userDto.id, passwordEncoder.encode(request.password))
    }

    private fun validateEmailCode(email: String, userId: Long, code: String) {
        val validatingEmailDto = validatingEmailService.findByEmail(email)
        if (validatingEmailDto.userId != userId || validatingEmailDto.code != code) {
            throw WithaengException.of(WithaengExceptionType.INVALID_INPUT, "Code가 올바르지 않습니다.")
        }
        validatingEmailService.deleteById(validatingEmailDto.id)
    }

    private fun UserSimpleDto.isValidUser(): Boolean {
        return roles.any { it == UserRole.USER }
    }

    private fun checkValidUserPassword(source: String, encryptedPassword: String) {
        if (!passwordEncoder.matches(source, encryptedPassword)) {
            throw WithaengException.of(WithaengExceptionType.AUTHENTICATION_FAILURE)
        }
    }

    private fun SignUpServiceRequest.toCreateUserDto(): CreateUserDto = CreateUserDto(
        email = email,
        password = passwordEncoder.encode(password),
        birth = birth,
        isMale = isMale
    )
}
