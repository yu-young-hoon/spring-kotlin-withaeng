package com.withaeng.api.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
@SecurityScheme(
    name = "Authorization",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
class SwaggerConfig {

    @Bean
    fun defaultSwagger(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Withaeng API")
                    .version("1.0")
            )
    }

    @Profile("prod")
    @Primary
    @Bean
    fun prodSwagger(defaultSwagger: OpenAPI): OpenAPI {
        return defaultSwagger
            .servers(
                listOf(
                    Server()
                        .url("https://api.withaeng.com")
                        .description("Production server"),
                )
            )
    }
}