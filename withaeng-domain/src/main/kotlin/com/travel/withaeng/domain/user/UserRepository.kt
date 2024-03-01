package com.travel.withaeng.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByProviderUniqueKey(providerUniqueKey: String): User?
}