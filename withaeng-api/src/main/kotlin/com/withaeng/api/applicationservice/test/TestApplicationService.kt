package com.withaeng.api.applicationservice.test

import com.withaeng.domain.user.UserService
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.jwt.JwtAgent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class TestApplicationService(
    private val jwtAgent: JwtAgent,
    private val userService: UserService
) {

    fun provideUserToken(userId: Long): String {
        return jwtAgent.provide(UserInfo.from(userService.findById(userId)))
    }

    @Transactional
    fun confirmUser(userId: Long) {
        userService.grantUserRole(userId)
    }
}