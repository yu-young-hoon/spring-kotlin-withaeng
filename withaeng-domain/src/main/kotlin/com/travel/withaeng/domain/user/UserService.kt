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
    fun create(createUserDto: CreateUserDto): UserSimpleDto {
        return userRepository.save(
            User.create(
                email = createUserDto.email,
                password = createUserDto.password,
                birth = createUserDto.birth,
                isMale = createUserDto.isMale
            )
        ).toSimpleDto()
    }

    fun findById(id: Long): UserSimpleDto {
        return userRepository.findByIdOrNull(id).getOrThrow().toSimpleDto()
    }

    fun findByEmailOrNull(email: String): UserSimpleDto? {
        return userRepository.findByEmail(email)?.toSimpleDto()
    }

    @Transactional
    fun addDetails(userId: Long, addDetailsUserDto: AddDetailsUserDto): UserDetailsDto {
        val user = userRepository.findByIdOrNull(userId).getOrThrow()
        addDetailsUserDto.mbti?.let { user.mbti = it }
        addDetailsUserDto.preferTravelType?.let { user.preferTravelType = it }
        addDetailsUserDto.preferTravelThemes?.let { user.preferTravelThemes = it.toSet() }
        addDetailsUserDto.consumeStyle?.let { user.consumeStyle = it }
        addDetailsUserDto.foodRestrictions?.let { user.foodRestrictions = it.toSet() }
        addDetailsUserDto.preferAccompanyGender?.let { user.preferAccompanyGender = it }
        addDetailsUserDto.smokingType?.let { user.smokingType = it }
        addDetailsUserDto.drinkingType?.let { user.drinkingType = it }
        return user.toDetailsDto()
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

    private fun User?.getOrThrow(): User {
        this ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 유저를 찾을 수 없습니다."
        )
        return this
    }
}
