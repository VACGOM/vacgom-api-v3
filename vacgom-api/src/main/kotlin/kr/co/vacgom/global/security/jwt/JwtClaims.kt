package kr.co.vacgom.global.security.jwt

import kr.co.vacgom.core.member.constants.GrantedAuthorityRole
import kr.co.vacgom.global.security.jwt.constants.JwtClaimConstants
import java.util.*

sealed interface JwtClaims {

    fun convertClaims(): Map<String, Any>

    data class UserClaims(
        val id: UUID = UUID.randomUUID(),
        val role: GrantedAuthorityRole
    ) : JwtClaims {
        override fun convertClaims(): Map<String, Any> = mapOf(JwtClaimConstants.USER_CLAIM to UserClaims::class.java)
    }

}


