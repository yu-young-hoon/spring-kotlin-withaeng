package com.withaeng.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.withaeng.api.common.WhiteList.getWhiteListForSecurityConfig
import com.withaeng.api.security.handler.HttpStatusAccessDeniedHandler
import com.withaeng.api.security.handler.HttpStatusAuthenticationEntryPoint
import com.withaeng.api.security.jwt.JwtAgent
import com.withaeng.api.security.jwt.JwtFilter
import com.withaeng.domain.user.UserRole
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
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
                it.anyRequest().hasAnyRole(UserRole.USER.getActualRoleName(), UserRole.ADMIN.getActualRoleName())
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
            it.ignoring().requestMatchers(*getWhiteListForSecurityConfig().toTypedArray())
                .requestMatchers(AntPathRequestMatcher("/swagger-ui", HttpMethod.GET.name()))
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
            registerCorsConfiguration("api/v1/**", configuration)
        }
    }

    companion object {
        private const val MAX_CORS_EXPIRE_SECONDS = 3600L
    }
}