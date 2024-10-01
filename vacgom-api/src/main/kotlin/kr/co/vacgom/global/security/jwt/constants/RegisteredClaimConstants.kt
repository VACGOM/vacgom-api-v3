package kr.co.vacgom.global.security.jwt.constants

enum class RegisteredClaimConstants(
    val subject: String
) {
    ID("id"),
    ROLE("role")
}
