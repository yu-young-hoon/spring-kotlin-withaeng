package com.withaeng.domain.converter

import jakarta.persistence.AttributeConverter

class AccompanyTagsConverter : AttributeConverter<Set<String>, String> {
    override fun convertToDatabaseColumn(attribute: Set<String>): String {
        return attribute
            .filter { it.isNotBlank() }
            .joinToString(DELIMITER)
    }

    override fun convertToEntityAttribute(data: String): Set<String> {
        return if (data.isBlank()) emptySet() else data.split(DELIMITER).toSet()
    }

    companion object {
        private const val DELIMITER = ","
    }
}