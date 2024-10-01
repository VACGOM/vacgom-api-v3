package kr.co.vacgom.api.member.presentation

import jakarta.validation.Valid
import kr.co.vacgom.api.member.application.dto.Member
import kr.co.vacgom.api.member.presentation.uri.MemberUri.REGISTER
import kr.co.vacgom.global.security.jwt.JwtTokenFactory
import kr.co.vacgom.global.uri.CommonUri.API_V3
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val jwtTokenFactory: JwtTokenFactory
) {
    @GetMapping(API_V3 + REGISTER)
    fun register(
        @RequestBody @Valid request: Member.Request
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }
}
