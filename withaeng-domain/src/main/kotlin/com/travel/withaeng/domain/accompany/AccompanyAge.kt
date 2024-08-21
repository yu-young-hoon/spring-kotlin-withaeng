package com.travel.withaeng.domain.accompany

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType

enum class AccompanyAge(
    val code: String,
    val value: Int,
) {
    MIN("MIN", 0),
    TWENTY("TWENTY", 20),
    TWENTY_FIVE("TWENTYFIVE", 25),
    THIRTY("THIRTY", 30),
    THIRTY_FIVE("THIRTYFIVE", 35),
    FORTY("FORTY", 40),
    FORTY_FIVE("FORTYFIVE", 45),
    OVER_FIFTY("FIFTY", 50),
    MAX("MAX", 99)
    ;

    companion object {
        fun fromValue(value: Int): AccompanyAge {
            return AccompanyAge.values().find { it.value == value }
                ?: throw WithaengException.of(
                    type = WithaengExceptionType.INVALID_ACCOMPANY_AGE_VALUE,
                    message = "$value is not a valid value for $this"
                )
        }
    }
}

class AccompanyAgeDeserializer : JsonDeserializer<AccompanyAge>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): AccompanyAge {
        val value = p.intValue
        return AccompanyAge.fromValue(value)
    }
}