package kr.co.vacgom.core.global.logger

import kr.co.vacgom.common.error.entity.ErrorEntity
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class LoggerUtils(
    private val logger: Logger
) {
    fun logErrorMessage(
        exception: Exception,
        errorEntity: ErrorEntity,
        errorMessage: String? = null
    ) {
        logger.error("TRACE | ${errorEntity.code} - ${errorEntity.message} | $errorMessage")
    }
}