package kr.co.vacgom.common.error

class BusinessException(
    val errorEntity: ErrorEntity
) : RuntimeException(errorEntity.message)
