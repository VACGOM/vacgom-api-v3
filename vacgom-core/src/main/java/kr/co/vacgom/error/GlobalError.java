package kr.co.vacgom.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum GlobalError implements ErrorEntity {

    GLOBAL_NOT_FOUND("해당 객체를 찾을 수 없습니다.", NOT_FOUND, "G_001"),
    INVALID_REQUEST_PARAM("요청 파라미터가 유효하지 않습니다.", BAD_REQUEST, "G_002");

    private final String message;
    private final HttpStatus httpStatus;
    private final String code;
}
