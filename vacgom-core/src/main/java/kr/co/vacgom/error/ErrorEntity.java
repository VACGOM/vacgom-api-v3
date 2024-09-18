package kr.co.vacgom.error;

import org.springframework.http.HttpStatus;

public interface ErrorEntity {
    String getCode();

    String getMessage();
    
    HttpStatus getHttpStatus();
}
