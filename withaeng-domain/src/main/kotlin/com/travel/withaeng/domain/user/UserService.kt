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
                nickname = createUserDto.nickname,
                socialType = createUserDto.socialType,
                profileImageUrl = createUserDto.profileImageUrl,
                providerUniqueKey = createUserDto.providerUniqueKey,
                birth = createUserDto.birth,
                isMale = createUserDto.isMale,
                bio = createUserDto.bio
            )
        ).toDto()
    }

    fun findByProviderUniqueKeyOrNull(providerUniqueKey: String): UserDto? {
        return userRepository.findByProviderUniqueKey(providerUniqueKey)?.toDto()
    }

}