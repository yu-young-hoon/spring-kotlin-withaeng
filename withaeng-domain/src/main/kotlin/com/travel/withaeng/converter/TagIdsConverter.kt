package com.travel.withaeng.converter

import jakarta.persistence.AttributeConverter

class TagIdsConverter : AttributeConverter<Set<Long>, String> {
    override fun convertToDatabaseColumn(attribute: Set<Long>): String {
        return attribute.joinToString(DELIMITER)
    }

    override fun convertToEntityAttribute(data: String): Set<Long> {
        return if (data.isBlank()) setOf()
        else data.split(DELIMITER).map { it.toLong() }.toSet()
    }

    companion object {
        private const val DELIMITER = ","
    }

}