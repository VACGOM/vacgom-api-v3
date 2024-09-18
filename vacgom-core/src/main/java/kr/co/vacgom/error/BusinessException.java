package kr.co.vacgom.error;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorEntity errorEntity;

    private BusinessException(ErrorEntity errorEntity) {
        super(errorEntity.getMessage());
        this.errorEntity = errorEntity;
    }

    public static BusinessException from(ErrorEntity errorEntity) {
        return new BusinessException(errorEntity);
    }

    public void logging() {
        log.error("[BusinessException] ({}) : {} | {}\nDetails: {}",
                this.getErrorEntity().getHttpStatus(),
                this.getErrorEntity().getMessage(),
                LocalDateTime.now(),
                this.getMessage()
        );
    }
}
