package kr.co.vacgom.handler

import kr.co.vacgom.common.error.GlobalError
import kr.co.vacgom.common.exception.BusinessException
import kr.co.vacgom.common.exception.dto.CommonErrorResponse
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler(
    private val log: Logger
) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        notValidException: MethodArgumentNotValidException
    ): ResponseEntity<CommonErrorResponse> {
        val fieldError: FieldError = notValidException.bindingResult.fieldErrors
            .firstOrNull() ?: throw BusinessException(GlobalError.INVALID_REQUEST_PARAM)
        val errorMessage = fieldError.defaultMessage ?: "Invalid input"

        val errorResponse = CommonErrorResponse(
            GlobalError.INVALID_REQUEST_PARAM,
            errorMessage
        )

        log.error(
            "[NotValidException] ({} - {}) | \n{}",
            GlobalError.INVALID_REQUEST_PARAM.code,
            GlobalError.INVALID_REQUEST_PARAM.message,
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

        log.error(
            "[BusinessException] ({} - {}) | \n{}",
            errorEntity.code,
            errorEntity.message,
            businessException.message
        )

        return ResponseEntity
            .status(errorEntity.httpStatus)
            .body(CommonErrorResponse(errorEntity))
    }
}
