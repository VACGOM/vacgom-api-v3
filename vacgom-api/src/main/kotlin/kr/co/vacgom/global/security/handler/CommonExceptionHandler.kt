package kr.co.vacgom.global.security.handler

import kr.co.vacgom.common.error.dto.CommonErrorResponse
import kr.co.vacgom.common.error.exception.BusinessException
import kr.co.vacgom.global.logger.LoggerUtils
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommonExceptionHandler(
    private val loggerUtils: LoggerUtils
) {
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(
        exception: BusinessException
    ): ResponseEntity<CommonErrorResponse> {
        val errorEntity = exception.errorEntity
        loggerUtils.logErrorResponse(exception, errorEntity)

        return ResponseEntity
            .status(errorEntity.httpStatus)
            .body(CommonErrorResponse(errorEntity))
    }
}
