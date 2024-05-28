package com.travel.withaeng.converter

import com.travel.withaeng.domain.user.UserPreferTravelTheme
import jakarta.persistence.AttributeConverter

class UserPreferTravelThemeConverter: AttributeConverter<Set<UserPreferTravelTheme>, String> {
    override fun convertToDatabaseColumn(attribute: Set<UserPreferTravelTheme>): String {
        return attribute.joinToString(DELIMITER) { it.name }
    }

    override fun convertToEntityAttribute(data: String): Set<UserPreferTravelTheme> {
        return data.split(DELIMITER).map { UserPreferTravelTheme.valueOf(it.trim()) }.toSet()
    }

    companion object {
        private const val DELIMITER = ","
    }
}