package kr.co.vacgom.api.member

import kr.co.vacgom.global.security.jwt.JwtTokenFactory
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val jwtTokenFactory: JwtTokenFactory
) {

    @GetMapping("/")
    fun nice(@RequestParam @NotNull temp: Int): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }
}
