package kr.co.vacgom.api.member.application.dto

class Member {

    data class Request(
        val name: String
    )

    sealed class Response {
        data class Success(
            val accessToken: String
        ) : Response()
    }
}