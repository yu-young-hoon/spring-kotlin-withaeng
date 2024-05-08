package com.travel.withaeng.applicationservice.auth

import com.travel.withaeng.applicationservice.auth.dto.SignUpServiceRequest
import com.travel.withaeng.applicationservice.auth.dto.UserResponse
import com.travel.withaeng.domain.user.CreateUserDto
import com.travel.withaeng.domain.user.UserService
import com.travel.withaeng.domain.validateemail.ValidatingEmailService
import com.travel.withaeng.security.authentication.UserInfo
import com.travel.withaeng.security.jwt.JwtAgent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class AuthApplicationService(
    private val userService: UserService,
    private val validatingEmailService: ValidatingEmailService,
    private val jwtAgent: JwtAgent
) {

    @Transactional
    fun signUp(request: SignUpServiceRequest): UserResponse {
        val userEmail = request.email
        val userDto = userService.findByEmailOrNull(request.email)
        if (userDto != null) {
            userService.deleteByEmail(userEmail)
        }
        val newUserDto = userService.createUser(request.toCreateUserDto())
        validatingEmailService.create(newUserDto.email, newUserDto.id, UUID.randomUUID().toString())
        return UserResponse(newUserDto.id, newUserDto.email, jwtAgent.provide(UserInfo.from(newUserDto)))
    }

    private fun SignUpServiceRequest.toCreateUserDto(): CreateUserDto = CreateUserDto(
        email = email,
        password = password,
        birth = birth,
        isMale = isMale
    )
}