package kr.co.vacgom.security.error

import kr.co.vacgom.common.error.ErrorEntity
import org.springframework.http.HttpStatus

enum class AuthError(
    override val message: String,
    override val httpStatus: HttpStatus,
    override val code: String,
) : ErrorEntity {
    INVALID_JWT_SIGNATURE("JWT 토큰 시그니처가 유효하지 않습니다.", HttpStatus.UNAUTHORIZED, "ATH_001"),
    INVALID_JWT_TOKEN("토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED, "ATH_002"),
    EXPIRED_JWT_TOKEN("토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED, "ATH_003"),
    UNSUPPORTED_JWT_TOKEN("지원되지 않는 토큰입니다.", HttpStatus.UNAUTHORIZED, "ATH_004"),
    UNSUPPORTED_PROVIDER("지원하지 않는 로그인 클라이언트입니다.", HttpStatus.BAD_REQUEST, "ATH_005")
}

