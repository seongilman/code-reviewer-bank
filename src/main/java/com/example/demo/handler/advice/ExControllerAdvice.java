package com.example.demo.handler.advice;

import com.example.demo.handler.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> exHandler(RuntimeException e) {
        log.error("", e);
        return handleExceptionInternal(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    private ResponseEntity<Object> handleExceptionInternal(int errorCode, String message) {
        return ResponseEntity.status(errorCode)
                .body(makeErrorResponse(errorCode, message));
    }

    private ErrorResponse makeErrorResponse(int errorCode, String message) {
        return ErrorResponse.builder()
                .status(errorCode)
                .message(message)
                .build();
    }
}