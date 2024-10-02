package kr.co.vacgom.api.member.application.dto

import java.util.*

class Member {

    data class Request(
        val name: String
    )

    data class Response(
        val id: UUID,
        val accessToken: String
    )
}