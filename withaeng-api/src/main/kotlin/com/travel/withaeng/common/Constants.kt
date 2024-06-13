package com.travel.withaeng.common

object Constants {
    object Authentication {
        const val BEARER_TYPE = "Bearer"
        const val BEARER_TOKEN_PREFIX_WITH_WHITESPACE = "$BEARER_TYPE "
    }

    object Url {
        const val BASE_URL = "localhost:3030/"
        const val VALIDATING_EMAIL_URL = BASE_URL + "validate-email"
    }
}
