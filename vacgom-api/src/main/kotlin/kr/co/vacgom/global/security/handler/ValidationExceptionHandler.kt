package kr.co.vacgom.global.security.handler

import kr.co.vacgom.common.error.dto.CommonErrorResponse
import kr.co.vacgom.common.error.entity.GlobalError
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ValidationExceptionHandler(
    private val loggerUtils: kr.co.vacgom.global.logger.LoggerUtils
) {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException
    ): ResponseEntity<CommonErrorResponse> {
        val errorEntity = GlobalError.INVALID_REQUEST_PARAM
        val traceMessage = exception.message
        return handleValidationException(exception, errorEntity, traceMessage)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException
    ): ResponseEntity<CommonErrorResponse> {
        val errorEntity = GlobalError.INVALID_REQUEST_PARAM
        val traceMessage = exception.message
        return handleValidationException(exception, errorEntity, traceMessage)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMethodArgumentTypeMismatchException(
        exception: MissingServletRequestParameterException
    ): ResponseEntity<CommonErrorResponse> {
        val errorEntity = GlobalError.INVALID_REQUEST_PARAM
        val traceMessage = exception.message
        return handleValidationException(exception, errorEntity, traceMessage)
    }

    private fun handleValidationException(
        exception: Exception,
        errorEntity: GlobalError,
        traceMessage: String?
    ): ResponseEntity<CommonErrorResponse> {
        loggerUtils.logErrorResponse(exception, errorEntity, traceMessage)

        val errorResponse = CommonErrorResponse(errorEntity)
        return ResponseEntity
            .badRequest()
            .body(errorResponse)
    }
}
