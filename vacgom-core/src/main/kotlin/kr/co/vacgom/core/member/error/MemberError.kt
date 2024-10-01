package kr.co.vacgom.core.member.error

import kr.co.vacgom.common.error.entity.ErrorEntity
import org.springframework.http.HttpStatus.NOT_FOUND

enum class MemberError(
    override val message: String,
    override val httpStatus: Int,
    override val code: String
) : ErrorEntity {
    MEMBER_NOT_FOUND("해당 유저를 찾을 수 없습니다.", NOT_FOUND.value(), "MBR_001")
}
