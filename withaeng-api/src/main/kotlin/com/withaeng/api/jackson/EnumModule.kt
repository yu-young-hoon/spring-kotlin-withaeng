package com.withaeng.api.jackson

import com.fasterxml.jackson.databind.module.SimpleModule
import com.withaeng.api.jackson.deserializer.SimpleEnumDeserializer
import com.withaeng.domain.user.*

class EnumModule : SimpleModule() {
    init {
        // User
        addSimpleEnumDeserializer<UserMbti>()
        addSimpleEnumDeserializer<UserPreferTravelType>()
        addSimpleEnumDeserializer<UserPreferTravelTheme>()
        addSimpleEnumDeserializer<UserConsumeStyle>()
        addSimpleEnumDeserializer<UserFoodRestriction>()
        addSimpleEnumDeserializer<UserPreferAccompanyGender>()
        addSimpleEnumDeserializer<UserSmokingType>()
        addSimpleEnumDeserializer<UserDrinkingType>()
    }

    private inline fun <reified T : Enum<T>> addSimpleEnumDeserializer() {
        addDeserializer(T::class.java, object : SimpleEnumDeserializer<T>(T::class.java) {})
    }
}
