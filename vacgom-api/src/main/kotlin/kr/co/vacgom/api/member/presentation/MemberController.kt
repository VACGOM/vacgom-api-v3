package kr.co.vacgom.api.member.presentation

import kr.co.vacgom.api.member.application.MemberCreateService
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberCreateService: MemberCreateService
) {
//    @GetMapping(API_V3 + REGISTER)
//    fun register(
//        @RequestBody @Valid request: Member.Request
//    ): ResponseEntity<Member.Response> = ResponseEntity.ok(memberCreateService.createMember(request))
}
