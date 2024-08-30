package com.withaeng.domain.converter

import com.withaeng.domain.user.UserFoodRestriction
import jakarta.persistence.AttributeConverter

class UserFoodRestrictionConverter : AttributeConverter<Set<UserFoodRestriction>, String> {
    override fun convertToDatabaseColumn(attribute: Set<UserFoodRestriction>): String {
        return attribute.joinToString(DELIMITER)
    }

    override fun convertToEntityAttribute(data: String): Set<UserFoodRestriction> {
        return if (data.isBlank()) emptySet()
        else data.split(DELIMITER).map { UserFoodRestriction.valueOf(it.trim()) }.toSet()
    }

    companion object {
        private const val DELIMITER = ","
    }
}
