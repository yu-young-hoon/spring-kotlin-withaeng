package com.travel.withaeng.domain.user

import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun createUser(createUserDto: CreateUserDto): UserDto {
        return userRepository.save(
            User.create(
                email = createUserDto.email,
                password = createUserDto.password,
                birth = createUserDto.birth,
                isMale = createUserDto.isMale
            )
        ).toDto()
    }

    fun findByEmailOrNull(email: String): UserDto? {
        return userRepository.findByEmail(email)?.toDto()
    }

    @Transactional
    fun grantUserRole(id: Long) {
        val user = userRepository.findByIdOrNull(id)
            ?: throw WithaengException.of(WithaengExceptionType.SYSTEM_FAIL)
        val newUserRoles = user.roles.filter { it != UserRole.NON_USER } + listOf(UserRole.USER)
        user.roles = newUserRoles.toSet()
    }

    @Transactional
    fun deleteByEmail(email: String) {
        return userRepository.deleteByEmail(email)
    }
}