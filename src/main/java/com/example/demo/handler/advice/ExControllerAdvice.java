package com.example.demo.handler.advice;

import com.example.demo.handler.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors()
//                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
//        return ResponseEntity.badRequest().body(errors);
//    }

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<?> illegalExHandler(IllegalStateException e) {
        log.error("[exceptionHandler] ex", e);
        handleExceptionInternal(400, e.getMessage());

        return handleExceptionInternal(400, e.getMessage());
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