package com.withaeng.domain.user

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.user.dto.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun create(createUserCommand: CreateUserCommand): UserSimpleDto {
        return userRepository.save(
            User.create(
                email = createUserCommand.email,
                nickname = createUserCommand.nickname,
                password = createUserCommand.password,
                birth = createUserCommand.birth,
                gender = createUserCommand.gender
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
    fun updateTravelPreference(userId: Long, command: UpdateTravelPreferenceCommand): UserDetailsDto {
        val user = userRepository.findByIdOrNull(userId).getOrThrow()
        command.nickname?.let { user.profile.nickname = it }
        command.mbti?.let { user.travelPreference?.mbti = it.toSet() }
        command.preferTravelType?.let { user.travelPreference?.preferTravelType = it }
        command.preferTravelThemes?.let { user.travelPreference?.preferTravelThemes = it.toSet() }
        command.consumeStyle?.let { user.travelPreference?.consumeStyle = it }
        command.foodRestrictions?.let { user.travelPreference?.foodRestrictions = it.toSet() }
        command.smokingType?.let { user.travelPreference?.smokingType = it }
        command.drinkingType?.let { user.travelPreference?.drinkingType = it }
        return user.toDetailsDto()
    }

    @Transactional
    fun updatePassword(userId: Long, password: String): UserSimpleDto {
        val user = userRepository.findByIdOrNull(userId).getOrThrow()
        user.password = password
        return user.toSimpleDto()
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
