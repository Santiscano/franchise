package com.accenture.santiago.franchise.handleResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ResponseModel<Void>> handleValidationException(WebExchangeBindException ex) {
        Map<String, Object> errors = new HashMap<>();

        ex.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ResponseModel<Void> responseModel = ResponseModel.error(
                HttpStatus.BAD_REQUEST,
                errors,
                "Validation Error"
        );

        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }

    // 400 -> Bad Request-> DTO validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseModel<Void>> handleValidateDtoException(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ResponseModel<Void> responseModel = ResponseModel.error(
                HttpStatus.BAD_REQUEST,
                errors,
                "Validation Error Generated"
        );

        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }


    // 400 -> Bad Request -> uso -> throw new HttpMessageNotReadableException("Malformed JSON or empty body");
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseModel<Void>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", "Request body is missing or malformed");

        ResponseModel<Void> responseModel = ResponseModel.error(
                HttpStatus.BAD_REQUEST,
                errors,
                "Malformed JSON or empty body"
        );

        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }
}
