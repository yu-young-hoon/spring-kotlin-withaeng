package com.travel.withaeng.jackson

import com.fasterxml.jackson.databind.module.SimpleModule
import com.travel.withaeng.domain.user.*
import com.travel.withaeng.jackson.deserializer.SimpleEnumDeserializer

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

    private inline fun <reified T: Enum<T>> addSimpleEnumDeserializer() {
        addDeserializer(T::class.java, object: SimpleEnumDeserializer<T>(T::class.java){})
    }
}
