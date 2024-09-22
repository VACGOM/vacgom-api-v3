package kr.co.vacgom.core.member.error

import kr.co.vacgom.common.error.ErrorEntity
import org.springframework.http.HttpStatus

enum class MemberError(
    override val message: String,
    override val httpStatus: HttpStatus,
    override val code: String
) : ErrorEntity {
    MEMBER_NOT_FOUND("해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, "MBR_001")
}
