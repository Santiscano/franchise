package com.accenture.santiago.franchise.utils;

import com.accenture.santiago.franchise.handleResponse.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ResponseUtils {

    public static <T> Mono<ResponseEntity<ResponseModel<T>>> handleMonoResponse(
            Mono<ResponseEntity<ResponseModel<T>>> mono,
            String notFoundMessage
    ) {
        return mono
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResponseModel.error(
                                HttpStatus.NOT_FOUND,
                                Map.of("error", notFoundMessage))
                        )))
                .onErrorResume(e -> Mono.just(ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                Map.of("error", e.getMessage()))
                        )));
    }

    public static <T> Flux<ResponseEntity<ResponseModel<T>>> handleFluxResponse(
            Flux<ResponseEntity<ResponseModel<T>>> flux,
            String notFoundMessage
    ) {
        return flux.switchIfEmpty(Flux.just(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResponseModel.error(
                                HttpStatus.NOT_FOUND,
                                Map.of("error", notFoundMessage)
                        ))
                ))
                .onErrorResume(e -> Flux.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                Map.of("error", e.getMessage()))
                        )
                ));
    }
}
