package kr.co.vacgom.core.global.handler

import kr.co.vacgom.common.error.dto.CommonErrorResponse
import kr.co.vacgom.common.error.entity.GlobalError
import kr.co.vacgom.common.error.exception.BusinessException
import kr.co.vacgom.core.global.logger.LoggerUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler(
    private val loggerUtils: LoggerUtils
) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        notValidException: MethodArgumentNotValidException
    ): ResponseEntity<CommonErrorResponse> {
        val errorEntity = GlobalError.INVALID_REQUEST_PARAM

        val fieldError: FieldError = notValidException.bindingResult.fieldErrors
            .firstOrNull() ?: throw BusinessException(errorEntity)
        val errorMessage = fieldError.defaultMessage
            ?: errorEntity.message

        val errorResponse = CommonErrorResponse(
            errorEntity,
            errorMessage
        )

        loggerUtils.logErrorMessage(
            notValidException,
            errorEntity,
            errorMessage
        )

        return ResponseEntity
            .badRequest()
            .body(errorResponse)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(
        businessException: BusinessException
    ): ResponseEntity<CommonErrorResponse> {

        val errorEntity = businessException.errorEntity

        loggerUtils.logErrorMessage(
            businessException,
            errorEntity,
            businessException.localizedMessage
        )

        return ResponseEntity
            .status(errorEntity.httpStatus)
            .body(CommonErrorResponse(errorEntity))
    }
}
