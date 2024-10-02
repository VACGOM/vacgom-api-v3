package kr.co.vacgom.api.member.application

import kr.co.vacgom.global.security.jwt.JwtProvider
import org.springframework.stereotype.Service

@Service
class MemberCreateService(
    private val jwtProvider: JwtProvider
) {

//    fun createMember(
//        request: Member.Request
//    ) = Member.Response(jwtProvider.issueToken(JwtType.ACCESS))
}