package kr.co.vacgom.common.error.entity

interface ErrorEntity {
    val code: String
    val httpStatus: Int
    val message: String?
}
