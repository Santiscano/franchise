package com.accenture.santiago.franchise.handleResponse;

import com.accenture.santiago.franchise.handleResponse.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

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

    // 400 -> Bad Request -> se ocasiona por un error en el payload
    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ResponseModel<Void>> handleServerWebInputException(ServerWebInputException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", "Invalid input: " + ex.getReason());

        ResponseModel<Void> responseModel = ResponseModel.error(
                HttpStatus.BAD_REQUEST,
                errors,
                "Invalid request payload"
        );

        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, DuplicateKeyException.class})
    public ResponseEntity<ResponseModel<Void>> handleConstraintViolation(Exception ex) {
        Map<String, Object> errors = new HashMap<>();

        String message = ex.getMessage();
        if (message != null && message.contains("duplicate key")) {
            errors.put("error", "The value you're trying to insert already exists and must be unique.");
        } else {
            errors.put("error", "A database constraint was violated.");
        }

        ResponseModel<Void> responseModel = ResponseModel.error(
                HttpStatus.BAD_REQUEST,
                errors,
                "Database Error"
        );

        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }

    // 404 -> NotFoundException -> uso -> throw new NotFoundException("Resource not found");
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseModel<Void>> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        ResponseModel<Void> responseModel = ResponseModel.error(
                HttpStatus.NOT_FOUND,
                errors,
                "Resource Not Found"
        );

        return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
    }
}
