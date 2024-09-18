package kr.co.vacgom.error;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommonErrorResponse {

    private final String timeStamp;

    private final String errorCode;

    private final String errorMessage;

    private CommonErrorResponse(
            String errorCode,
            String errorMessage
    ) {
        this.timeStamp = LocalDateTime.now().toString();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static CommonErrorResponse from(ErrorEntity errorEntity) {
        return new CommonErrorResponse(errorEntity.getCode(), errorEntity.getMessage());
    }

    public static CommonErrorResponse of(ErrorEntity errorEntity, String errorMessage) {
        return new CommonErrorResponse(errorEntity.getCode(), errorMessage);
    }
}
