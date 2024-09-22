package kr.co.vacgom.common.exception

import kr.co.vacgom.common.error.ErrorEntity

class BusinessException(
    val errorEntity: ErrorEntity
) : RuntimeException(errorEntity.message)
