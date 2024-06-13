package com.travel.withaeng.applicationservice.user

import com.travel.withaeng.applicationservice.user.dto.AddUserDetailsServerRequest
import com.travel.withaeng.applicationservice.user.dto.UserDetailsResponse
import com.travel.withaeng.applicationservice.user.dto.toDomainDto
import com.travel.withaeng.domain.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class UserApplicationService(private val userService: UserService) {

    @Transactional
    fun addDetails(addUserDetailsServerRequest: AddUserDetailsServerRequest): UserDetailsResponse {
        val userId = addUserDetailsServerRequest.userId
        val userDetailsDto = userService.addDetails(userId, addUserDetailsServerRequest.toDomainDto())
        return UserDetailsResponse.from(userDetailsDto)
    }

}
