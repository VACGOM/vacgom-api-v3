package kr.co.vacgom.api.member

import kr.co.vacgom.core.global.security.jwt.JwtTokenFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val jwtTokenFactory: JwtTokenFactory
) {

    @GetMapping("/")
    fun nice(): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }
}