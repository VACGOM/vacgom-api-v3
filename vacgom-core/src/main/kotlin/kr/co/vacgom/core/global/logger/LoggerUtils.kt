package kr.co.vacgom.core.global.logger

import kr.co.vacgom.common.error.entity.ErrorEntity
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class LoggerUtils(
    private val logger: Logger
) {
    fun logErrorResponse(
        exception: Exception,
        error: ErrorEntity,
        traceMessage: String? = null
    ) {
        when (traceMessage) {
            null -> logger.error(TRACE, error.code, error.httpStatus, error.message)
            else -> logger.error(TRACE_WITH_MSG, error.code, error.httpStatus, error.message, traceMessage)
        }
    }

    private companion object {
        const val REQUEST = "REQ | {}: {} ({}) - {}"
        const val TRACE = "ERR | {} ({}) - {}"
        const val TRACE_WITH_MSG = "$TRACE | {}"
    }
}