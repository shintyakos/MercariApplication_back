package com.stake.mercariapplication.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.stake.mercariapplication.repository.UserRepository
import com.stake.mercariapplication.util.Logger
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
object JwtUtil {
    private val secret = Hash()

    // 有効期限は1時間
    private const val EXPIRATION_DATE: Long = 1000L * 60L * 60L * 1L

    @Autowired
    private lateinit var userRepository: UserRepository

    fun generateToken(username: String): String {
        val issuedAt: LocalDateTime = LocalDateTime.now()
        val issuedAtDate: Date = issuedAt.atZone(ZoneId.systemDefault()).toInstant().let { Date.from(it) }
        val expirationDate = Date(issuedAtDate.time + EXPIRATION_DATE)

        return JWT.create()
            .withIssuer(secret.tokenIssuer)
            .withSubject(secret.tokenSubject)
            .withAudience(username)
            .withNotBefore(issuedAtDate)
            .withExpiresAt(expirationDate)
            .sign(Algorithm.HMAC256(secret.secretKey))
    }

    private fun verifyToken(token: String): DecodedJWT? {
        return try {
            JWT.require(Algorithm.HMAC256(secret.secretKey))
                .withIssuer(secret.tokenIssuer)
                .withSubject(secret.tokenSubject)
                .build()
                .verify(token)
        } catch (error: Exception) {
            Logger.error("Token verification failed: $error")
            return null
        }
    }

    fun authorize(request: HttpServletRequest): Boolean {
        // Authorizationヘッダーが存在しない場合はfalse
        val authorization = request.getHeader("Authorization") ?: return false

        if (authorization.isEmpty()) {
            return false
        }

        // Bearerトークンでない場合はfalse
        if (authorization.indexOf("Bearer ") != 0) {
            Logger.error("Authorization header is invalid")
            return false
        }

        val token = authorization.substring(7)
        verifyToken(token)?.let {
            // UsernameがDBに存在しない場合はfalse
            userRepository.findByUserName(it.audience[0])?.let { user ->
                request.setAttribute("username", user.userName)
            } ?: return false
        } ?: return false

        return true
    }
}