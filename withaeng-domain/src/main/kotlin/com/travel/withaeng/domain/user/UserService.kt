package com.travel.withaeng.domain.user

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
    fun deleteByEmail(email: String) {
        return userRepository.deleteByEmail(email)
    }
}