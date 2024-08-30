package com.withaeng.api.jackson.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

open class SimpleEnumDeserializer<T : Enum<T>>(
    private val enumType: Class<T>
) : JsonDeserializer<T>() {

    override fun deserialize(parser: JsonParser, ctxt: DeserializationContext): T {
        val node = parser.text
        return java.lang.Enum.valueOf(enumType, node.uppercase())
    }
}