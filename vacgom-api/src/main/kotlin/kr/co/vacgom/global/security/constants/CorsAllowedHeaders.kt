package kr.co.vacgom.global.security.constants

enum class CorsAllowedHeaders(val value: String) {
    AUTHORIZATION("Authorization"),
    CONTENT_TYPE("Content-Type"),
    ACCEPT("Accept")
}