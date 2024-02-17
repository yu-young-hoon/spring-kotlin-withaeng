package com.travel.withaeng.converter

import com.travel.withaeng.domain.user.UserRole
import jakarta.persistence.AttributeConverter

class UserRoleConverter : AttributeConverter<Set<UserRole>, String> {
    override fun convertToDatabaseColumn(attribute: Set<UserRole>): String {
        return attribute.joinToString(DELIMITER) { it.name }
    }

    override fun convertToEntityAttribute(data: String): Set<UserRole> {
        return data.split(DELIMITER).map { UserRole.valueOf(it.trim()) }.toSet()
    }

    companion object {
        private const val DELIMITER = ","
    }
}