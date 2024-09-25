package kr.co.vacgom.core.global.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import kr.co.vacgom.common.error.exception.BusinessException
import kr.co.vacgom.core.global.security.error.AuthError
import kr.co.vacgom.core.global.security.jwt.constants.RegisteredClaimConstants
import kr.co.vacgom.core.member.application.MemberService
import kr.co.vacgom.core.member.domain.Member
import kr.co.vacgom.core.member.domain.constants.GrantedAuthorityRole
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenFactory(
    @Value("\${jwt.key}")
    private val rawSecretKey: String,

    @Value("\${jwt.validity.access-token}")
    private val accessTokenValidity: Long,

    private val memberService: MemberService
) {
    private val decodedSecretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(rawSecretKey))

    fun generateAccessToken(
        member: Member
    ): String {
        val now = Date()
        val expiration = Date(Date().time + accessTokenValidity)

        return Jwts.builder()
            .issuedAt(now)
            .claim(RegisteredClaimConstants.ID.subject, member.id)
            .claim(RegisteredClaimConstants.ROLE.subject, member.role)
            .signWith(decodedSecretKey)
            .expiration(expiration)
            .compact()
    }

    fun getValidUserAuthentication(
        token: String
    ): PreAuthenticatedAuthenticationToken {
        val verifiedClaims = getVerifiedTokenClaims(token)
        val longMemberId = verifiedClaims[RegisteredClaimConstants.ID.subject]?.toString()?.toLong()!!
        val stringMemberId = verifiedClaims[RegisteredClaimConstants.ID.subject]?.toString()
        val role = verifiedClaims[RegisteredClaimConstants.ROLE.subject].toString()

        validateUserId(longMemberId)
        validateUserRole(role)

        val authorities = listOf(SimpleGrantedAuthority(role))

        val user = User.builder()
            .username(stringMemberId)
            .authorities(authorities)
            .build()

        return PreAuthenticatedAuthenticationToken(user, null, authorities)
    }

    private fun getVerifiedTokenClaims(
        token: String
    ): Claims {
        return try {
            Jwts.parser()
                .verifyWith(decodedSecretKey)
                .build()
                .parseSignedClaims(token)
                .payload
        } catch (exception: Exception) {
            throw when (exception) {
                is SecurityException
                    -> BusinessException(AuthError.INVALID_JWT_SIGNATURE)

                is MalformedJwtException
                    -> BusinessException(AuthError.INVALID_JWT_TOKEN)

                is ExpiredJwtException
                    -> BusinessException(AuthError.EXPIRED_JWT_TOKEN)

                is UnsupportedJwtException, is IllegalArgumentException
                    -> BusinessException(AuthError.UNSUPPORTED_JWT_TOKEN)

                else -> BusinessException(AuthError.UNDEFINED_TOKEN_ERROR)
            }
        }
    }

    private fun validateUserRole(role: String) {
        if (GrantedAuthorityRole.entries.toTypedArray().none { it.name == role }) {
            throw BusinessException(AuthError.UNKNOWN_CLAIM_CONSTANTS)
        }
    }

    private fun validateUserId(memberId: Long) {
        if (memberService.istValidMember(memberId)) {
            throw BusinessException(AuthError.INVALID_USER_ID)
        }
    }
}
