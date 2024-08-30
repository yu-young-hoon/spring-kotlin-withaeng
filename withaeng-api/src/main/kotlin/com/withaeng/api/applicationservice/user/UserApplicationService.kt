package com.withaeng.api.applicationservice.user

import com.withaeng.api.applicationservice.user.dto.AddUserDetailsServerRequest
import com.withaeng.api.applicationservice.user.dto.UserDetailsResponse
import com.withaeng.api.applicationservice.user.dto.toDomainDto
import com.withaeng.domain.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class UserApplicationService(
    private val userService: UserService
) {

    @Transactional
    fun addDetails(addUserDetailsServerRequest: AddUserDetailsServerRequest): UserDetailsResponse {
        val userId = addUserDetailsServerRequest.userId
        val userDetailsDto = userService.addDetails(userId, addUserDetailsServerRequest.toDomainDto())
        return UserDetailsResponse.from(userDetailsDto)
    }

}
