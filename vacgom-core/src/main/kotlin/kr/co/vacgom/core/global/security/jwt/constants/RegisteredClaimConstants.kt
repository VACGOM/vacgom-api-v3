package kr.co.vacgom.core.global.security.jwt.constants

enum class RegisteredClaimConstants(
    val subject: String
) {
    ID("id"),
    ROLE("role")
}
