package com.example.car_selling.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    private ResponseEntity<ApiError> buildResponse(HttpStatus status, String message, String path) {
        ApiError error = new ApiError(
                status.value(),
                status,
                message,
                path,
                java.time.LocalDateTime.now()
        );
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleException(MethodArgumentNotValidException ex, HttpServletRequest req) {

        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiError> handleAlreadyExist(UserException ex, HttpServletRequest req){
        return buildResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), req.getRequestURI());
    }



    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ApiError> handleAlreadyExist(NotFound ex, HttpServletRequest req){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), req.getRequestURI());
    }
}
