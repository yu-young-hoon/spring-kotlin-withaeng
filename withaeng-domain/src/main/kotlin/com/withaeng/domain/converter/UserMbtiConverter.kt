package com.withaeng.domain.converter

import com.withaeng.domain.user.UserMbti
import jakarta.persistence.AttributeConverter

class UserMbtiConverter : AttributeConverter<Set<UserMbti>, String> {
    override fun convertToDatabaseColumn(attribute: Set<UserMbti>): String {
        return attribute.joinToString(DELIMITER) { it.name }
    }

    override fun convertToEntityAttribute(data: String): Set<UserMbti> {
        return if (data.isBlank()) return emptySet()
        else data.split(DELIMITER).map { UserMbti.valueOf(it.trim()) }.toSet()
    }

    companion object {
        private const val DELIMITER = ","
    }
}