package kr.co.vacgom.common.error.exception

import kr.co.vacgom.common.error.entity.ErrorEntity

class BusinessException(
    val errorEntity: ErrorEntity
) : RuntimeException(errorEntity.message)
