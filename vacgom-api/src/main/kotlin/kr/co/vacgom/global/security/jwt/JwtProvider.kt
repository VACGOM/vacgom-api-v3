package kr.co.vacgom.global.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import kr.co.vacgom.core.member.constants.GrantedAuthorityRole
import kr.co.vacgom.global.security.jwt.constants.JwtClaimConstants.USER_CLAIM
import kr.co.vacgom.global.security.jwt.constants.JwtType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(
    @Value("\${jwt.key}")
    private val encodedSecretKey: String,

    @Value("\${jwt.validity.access-token}")
    private val accessTokenValidity: Long
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(encodedSecretKey.toByteArray())

    fun issueToken(jwtType: JwtType): String {
        val now = Date()
        val expiration = Date(now.time + accessTokenValidity)

        return Jwts.builder()
            .issuedAt(now)
            .claim(USER_CLAIM, JwtClaims.UserClaims(role = GrantedAuthorityRole.USER))
            .signWith(secretKey)
            .expiration(expiration)
            .compact()
    }

//    fun getValidUserAuthentication(
//        token: String
//    ): PreAuthenticatedAuthenticationToken {
//
//        val user = SecurityProperties.User.builder()
//            .username(stringMemberId)
//            .authorities(authorities)
//            .build()
//
//        return PreAuthenticatedAuthenticationToken(user, null, authorities)
//    }

//    private fun getVerifiedTokenClaims(
//        token: String
//    ): EnumMap<RegisteredClaimConstants, String> =
//        try {
//            val claims = Jwts.parser()
//                .verifyWith(secretKey)
//                .build()
//                .parseSignedClaims(token)
//                .payload
//
//            val longMemberId = verifiedClaims[RegisteredClaimConstants.ID.value]?
//            val role = verifiedClaims[RegisteredClaimConstants.ROLE.value].toString()
//            val authorities = listOf(SimpleGrantedAuthority(role))
//
//
//        } catch (exception: Exception) {
//            throw when (exception) {
//                is SecurityException
//                    -> BusinessException(AuthError.INVALID_JWT_SIGNATURE)
//
//                is MalformedJwtException,
//                is ExpiredJwtException
//                    -> BusinessException(AuthError.INVALID_JWT_TOKEN)
//
//                is UnsupportedJwtException,
//                is IllegalArgumentException
//                    -> BusinessException(AuthError.UNSUPPORTED_JWT_TOKEN)
//
//                else
//                    -> BusinessException(AuthError.UNDEFINED_TOKEN_ERROR)
//            }
//        }
}
