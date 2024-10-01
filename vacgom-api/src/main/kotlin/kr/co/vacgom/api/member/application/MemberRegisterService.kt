package kr.co.vacgom.api.member.application

import kr.co.vacgom.api.member.application.dto.Member
import kr.co.vacgom.global.security.jwt.JwtTokenFactory
import org.springframework.stereotype.Service

@Service
class MemberCreateService(
    private val jwtTokenFactory: JwtTokenFactory
) {

    fun createMember(
        request: Member.Request
    ) = Member.Response.Success(jwtTokenFactory.generateAccessToken())
}