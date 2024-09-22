package kr.co.vacgom.security.jwt.constants

enum class RegisteredClaimConstants(
    val subject: String
) {
    ID("id"),
    ROLE("role")
}
