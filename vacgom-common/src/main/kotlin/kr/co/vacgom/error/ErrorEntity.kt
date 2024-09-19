package kr.co.vacgom.error

import org.springframework.http.HttpStatus

interface ErrorEntity {
    val code: String
    val httpStatus: HttpStatus
    val message: String?
}
