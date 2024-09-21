package kr.co.vacgom.common.error

import org.springframework.http.HttpStatus

interface ErrorEntity {
    val code: String
    val httpStatus: HttpStatus
    val message: String?
}
