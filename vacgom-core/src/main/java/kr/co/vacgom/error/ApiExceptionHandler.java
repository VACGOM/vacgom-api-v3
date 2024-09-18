package kr.co.vacgom.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kr.co.vacgom.error.GlobalError.INVALID_REQUEST_PARAM;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException notValidException
    ) {
        FieldError fieldError = notValidException.getBindingResult().getFieldErrors()
                .stream().findFirst()
                .orElseThrow(() -> BusinessException.from(INVALID_REQUEST_PARAM));

        CommonErrorResponse errorResponse = CommonErrorResponse.of(
                INVALID_REQUEST_PARAM,
                fieldError.getDefaultMessage()
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonErrorResponse> handleBusinessException(BusinessException businessException) {
        businessException.logging();

        ErrorEntity errorEntity = businessException.getErrorEntity();
        return ResponseEntity.status(errorEntity.getHttpStatus()).body(CommonErrorResponse.from(errorEntity));
    }
}
