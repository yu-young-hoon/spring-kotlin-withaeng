package com.travel.withaeng.converter

import com.travel.withaeng.domain.user.UserFoodRestriction
import jakarta.persistence.AttributeConverter

class UserFoodRestrictionConverter : AttributeConverter<Set<UserFoodRestriction>, String> {
    override fun convertToDatabaseColumn(attribute: Set<UserFoodRestriction>): String {
        return attribute.joinToString(DELIMITER)
    }

    override fun convertToEntityAttribute(data: String): Set<UserFoodRestriction> {
        return data.split(DELIMITER).map { UserFoodRestriction.valueOf(it.trim()) }.toSet()
    }

    companion object {
        private const val DELIMITER = ","
    }
}