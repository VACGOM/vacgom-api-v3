package kr.co.vacgom.common.error

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

        val errorResponse = CommonErrorResponse(
            GlobalError.INVALID_REQUEST_PARAM,
            fieldError.defaultMessage ?: "Invalid input"
        )

        return ResponseEntity.badRequest().body(errorResponse)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(
        businessException: BusinessException
    ): ResponseEntity<CommonErrorResponse> {
        businessException.logging()

        val errorEntity = businessException.errorEntity
        return ResponseEntity
            .status(errorEntity.httpStatus)
            .body(CommonErrorResponse(errorEntity))
    }
}
