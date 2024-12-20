package com.withaeng.external.client

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient


@Service
class GoogleClient(
    @Value("\${redirect-url}")
    private var redirectUrl: String,
) {
    fun getToken(code: String): TokenResponse? {
        val tokenRequest = TokenRequest(code.replace("%2F", "/"), redirectUri = redirectUrl)
        val restClient = RestClient.builder().requestFactory(HttpComponentsClientHttpRequestFactory()).build()
        val response = restClient.post()
            .uri("https://oauth2.googleapis.com/token")
            .contentType(APPLICATION_JSON)
            .body(tokenRequest)
            .retrieve()
            .toEntity(TokenResponse::class.java)
        return response.body
    }

    data class TokenRequest(
        val code: String,
        val clientId: String = "1083774231549-gham8kln42p4koag86qhuhtouuoakm14.apps.googleusercontent.com",
        val clientSecret: String = "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",
        val redirectUri: String = "http://localhost:3000/google",
        val grantType: String = "authorization_code",
    )

    data class TokenResponse(
        @JsonProperty("access_token")
        val accessToken: String,
    )

    fun getMe(token: String): MeResponse? {
        val restClient = RestClient.create();
        val response = restClient.get()
            .uri("https://www.googleapis.com/userinfo/v2/me")
            .header("Authorization", "Bearer $token")
            .retrieve()
            .toEntity(MeResponse::class.java)
        return response.body
    }
    data class MeResponse(
        val id: String,
        val name: String,
        val email: String,
    )
    fun getInfo(token: String): Info? {
        val restClient = RestClient.create();
        val response = restClient.get()
            .uri("https://people.googleapis.com/v1/people/me?personFields=birthdays,genders")
            .header("Authorization", "Bearer $token")
            .retrieve()
            .toEntity(Info::class.java)
        return response.body
    }
    data class Info(
        val genders: List<Gender>,
        val birthdays: List<Birthday>
    ) {
        data class Gender(
            val metadata: Metadata,
            val value: String,
            val formattedValue: String
        ) {
            data class Metadata(
                val primary: Boolean,
                val source: Source
            ) {
                data class Source(
                    val type: String,
                    val id: String
                )
            }
        }

        data class Birthday(
            val metadata: Metadata,
            val date: Date
        ) {
            data class Metadata(
                val primary: Boolean? = null,
                val source: Source
            ) {
                data class Source(
                    val type: String,
                    val id: String
                )
            }

            data class Date(
                val year: Int,
                val month: Int,
                val day: Int
            )
        }
    }
}