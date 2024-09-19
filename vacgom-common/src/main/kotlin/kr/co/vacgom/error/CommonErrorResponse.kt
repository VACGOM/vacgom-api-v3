package kr.co.vacgom.error

import java.time.LocalDateTime

data class CommonErrorResponse(
    val timeStamp: String = LocalDateTime.now().toString(),
    val errorCode: String,
    val errorMessage: String?
) {
    constructor(errorEntity: ErrorEntity) : this(
        errorCode = errorEntity.code,
        errorMessage = errorEntity.message
    )

    constructor(errorEntity: ErrorEntity, errorMessage: String) : this(
        errorCode = errorEntity.code,
        errorMessage = errorMessage
    )
}