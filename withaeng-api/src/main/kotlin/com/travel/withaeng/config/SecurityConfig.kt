package com.travel.withaeng.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.travel.withaeng.security.handler.HttpStatusAccessDeniedHandler
import com.travel.withaeng.security.handler.HttpStatusAuthenticationEntryPoint
import com.travel.withaeng.security.jwt.JwtAgent
import com.travel.withaeng.security.jwt.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtAgent: JwtAgent, private val objectMapper: ObjectMapper) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .authorizeHttpRequests {
                it.anyRequest().permitAll() // TODO: 추후 인가 추가
            }
            .addFilterBefore(JwtFilter(jwtAgent, objectMapper), UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(HttpStatusAuthenticationEntryPoint())
                it.accessDeniedHandler(HttpStatusAccessDeniedHandler())
            }
            .build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer {
            it.ignoring().requestMatchers(*WHITE_LIST.toTypedArray())
        }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            addAllowedOriginPattern("*")
            addAllowedMethod("*")
            addAllowedHeader("*")
            exposedHeaders = listOf(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE)
            maxAge = MAX_CORS_EXPIRE_SECONDS
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/v1/**", configuration)
        }
    }

    companion object {
        private const val MAX_CORS_EXPIRE_SECONDS = 3600L

        private val WHITE_LIST = listOf(
            // swagger
            "/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**",
            // error page
            "/error/**",
            // Auth endpoints
            "/v1/auth/**",
        )
    }
}