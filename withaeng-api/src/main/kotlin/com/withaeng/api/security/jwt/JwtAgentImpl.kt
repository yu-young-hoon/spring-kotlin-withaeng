package com.withaeng.api.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import java.security.Key
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

class JwtAgentImpl(
    private val objectMapper: ObjectMapper,
    @Value("\${jwt.issuer}") private val issuer: String,
    @Value("\${jwt.secret-key}") key: String,
) : JwtAgent {

    private val secretKey: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key))
    private val jwtParser: JwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build()

    override fun provide(userInfo: UserInfo): String {
        val issuerDateTime = LocalDateTime.now(ASIA_SEOUL_ZONE_ID)
        val expiredDateTime = issuerDateTime.plusDays(TOKEN_EXPIRE_DAY)
        return Jwts.builder()
            .setHeaderParam(KEY_TOKEN_TYPE, TOKEN_TYPE_JWT)
            .setSubject(createSubject(userInfo.email!!))
            .setAudience("${userInfo.email}|${userInfo.id}")
            .setIssuer(issuer)
            .setIssuedAt(issuerDateTime.convertToDate())
            .setExpiration(expiredDateTime.convertToDate())
            .claim(KEY_CLAIM_INFO, userInfo)
            .signWith(secretKey)
            .compact()
    }

    override fun extractUserInfoFromToken(token: String): UserInfo {
        return jwtParser.parseClaimsJws(token).body?.let { claims ->
            objectMapper.convertValue(claims[KEY_CLAIM_INFO], UserInfo::class.java)
        } ?: throw WithaengException.of(WithaengExceptionType.INVALID_USER_AUTH_TOKEN)
    }

    private fun LocalDateTime.convertToDate(): Date = Date.from(this.toInstant(ASIA_SEOUL_ZONE_OFFSET))

    private fun createSubject(subject: String): String = "jwt-user-$subject"

    companion object {
        private const val KEY_CLAIM_INFO = "info"
        private const val KEY_TOKEN_TYPE = "typ"
        private const val TOKEN_TYPE_JWT = "JWT"

        private val ASIA_SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul")
        private val ASIA_SEOUL_ZONE_OFFSET = ZoneOffset.of("+09:00")

        private const val TOKEN_EXPIRE_DAY = 7L // 임시 JWT 만료기간
    }
}