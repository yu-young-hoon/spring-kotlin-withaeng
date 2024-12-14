package com.withaeng.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun deleteByEmail(email: String)
    fun findByGoogleId(googleId: String): User?
}