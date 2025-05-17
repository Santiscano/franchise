package com.accenture.santiago.franchise.handleResponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResponseModel<T> {
    private Map<String, Object> statusCode;
    private String message;
    private T data;
    private Map<String, Object> errors;
    private Boolean success;
    private LocalDateTime serverTime;

    private ResponseModel() {
        this.serverTime = LocalDateTime.now();
    }

    private Map<String, Object> buildStatusCode(HttpStatus status) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", status.value());
        resultMap.put("status", status.name());
        return resultMap;
    }

    // Métodos estáticos para crear instancias
    public static <T> ResponseModel<T> success(HttpStatus status, T data) {
        ResponseModel<T> response = new ResponseModel<>();
        response.statusCode = response.buildStatusCode(status);
        response.data = data;
        response.success = true;
        return response;
    }

    public static <T> ResponseModel<T> success(HttpStatus status, T data, String message) {
        ResponseModel<T> response = new ResponseModel<>();
        response.statusCode = response.buildStatusCode(status);
        response.data = data;
        response.message = message;
        response.success = true;
        return response;
    }

    public static <T> ResponseModel<T> error(HttpStatus status, String message) {
        ResponseModel<T> response = new ResponseModel<>();
        response.statusCode = response.buildStatusCode(status);
        response.message = message;
        response.success = false;
        return response;
    }

    public static <T> ResponseModel<T> error(HttpStatus status, Map<String, Object> errors) {
        ResponseModel<T> response = new ResponseModel<>();
        response.statusCode = response.buildStatusCode(status);
        response.errors = errors;
        response.success = false;
        return response;
    }

    public static <T> ResponseModel<T> error(HttpStatus status, Map<String, Object> errors, String message) {
        ResponseModel<T> response = new ResponseModel<>();
        response.statusCode = response.buildStatusCode(status);
        response.errors = errors;
        response.message = message;
        response.success = false;
        return response;
    }
}
