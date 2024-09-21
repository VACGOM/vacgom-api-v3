package kr.co.vacgom.common.error

import org.slf4j.LoggerFactory
import java.time.LocalDateTime

class BusinessException(
    val errorEntity: ErrorEntity
) : RuntimeException(errorEntity.message) {

    private val log = LoggerFactory.getLogger(BusinessException::class.java)


    fun logging() {
        log.error(
            "[BusinessException] ({}) : {} | {}\nDetails: {}",
            errorEntity.httpStatus,
            errorEntity.message,
            LocalDateTime.now(),
            message
        )
    }
}
