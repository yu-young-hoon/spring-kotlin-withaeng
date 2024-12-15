package com.withaeng.api.config

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
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAgent: JwtAgent,
) {

    companion object {
        private const val MAX_CORS_EXPIRE_SECONDS = 3600L
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .headers {
                it.frameOptions { frameOptionsConfig ->
                    frameOptionsConfig.sameOrigin()
                }
            }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/",
                        "/home",
                        "/instagram",
                        "/auth",
                        "/api/v1/auth/**",
                        "/api/v1/test/**",
                        "/api/v1/destinations",
                        "/h2-console/**",
                    ).permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/accompany/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/accompany/**")
                    .hasRole(UserRole.ADMIN.getActualRoleName())
                    .anyRequest().hasAnyRole(UserRole.USER.getActualRoleName(), UserRole.ADMIN.getActualRoleName())
            }
            .addFilterBefore(JwtFilter(jwtAgent), UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(HttpStatusAuthenticationEntryPoint())
                it.accessDeniedHandler(HttpStatusAccessDeniedHandler())
            }
            .build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer {
            it.ignoring().requestMatchers(HttpMethod.GET, *getWhiteListForSecurityConfig().toTypedArray())
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
}