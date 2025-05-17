package com.accenture.santiago.franchise.app.franchise.controller;

import com.accenture.santiago.franchise.app.franchise.dto.SaveFranchiseDto;
import com.accenture.santiago.franchise.app.franchise.dto.UpdateFranchiseNameDto;
import com.accenture.santiago.franchise.app.franchise.entity.FranchiseEntity;
import com.accenture.santiago.franchise.app.franchise.service.FranchiseService;
import com.accenture.santiago.franchise.handleResponse.ResponseModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    // controller methods
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ResponseModel<FranchiseEntity>>> findById(@PathVariable Integer id) {
        return franchiseService.findById(id)
                .map(franchise -> ResponseEntity.ok(
                        ResponseModel.success(
                                HttpStatus.OK,
                                franchise,
                                "Franchise found successfully"
                        )
                ))
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResponseModel.error(
                                HttpStatus.NOT_FOUND,
                                Map.of("error", "Franchise not found")))))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                Map.of("error", e.getMessage())))));
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<ResponseModel<FranchiseEntity>>> findByName(@PathVariable String name) {
        return franchiseService.findByName(name)
                .map(franchise -> ResponseEntity.ok(
                        ResponseModel.success(HttpStatus.OK, franchise, "Franchise found")))
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResponseModel.error(HttpStatus.NOT_FOUND, Map.of("error", "Franchise not found")))))
                .onErrorResume(e -> Mono.just(ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("error", e.getMessage())))));
    }

    // ========================= FIND ALL =========================
    @GetMapping
    public Mono<ResponseEntity<ResponseModel<List<FranchiseEntity>>>> findAll() {
        return franchiseService.findAll()
                .collectList()
                .map(list -> ResponseEntity.ok(
                        ResponseModel.success(HttpStatus.OK, list, "Franchises retrieved successfully")))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("error", e.getMessage())))));
    }

    @PostMapping
    public Mono<ResponseEntity<ResponseModel<FranchiseEntity>>> saveFranchise(@Valid @RequestBody SaveFranchiseDto dto) {
        return franchiseService.save(dto)
                .map(franchise -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(ResponseModel.success(
                              HttpStatus.CREATED,
                                franchise,
                                "Franchise created successfully"
                        ))
                )
                .onErrorResume(e -> {
                    Map<String, Object> errors = new HashMap<>();
                    errors.put("error", e.getMessage());
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(ResponseModel.error(
                                    HttpStatus.BAD_REQUEST,
                                    errors
                            ))
                    );
                });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ResponseModel<FranchiseEntity>>> updateFranchiseName(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateFranchiseNameDto dto) {

        return franchiseService.updateName(id, dto)
                .map(updated -> ResponseEntity.ok(
                        ResponseModel.success(
                                HttpStatus.OK,
                                updated,
                                "Franchise name updated successfully"
                        )))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseModel.error(
                                HttpStatus.NOT_FOUND,
                                Map.of("error", "Franchise not found")))))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("error", e.getMessage())))));
    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ResponseModel<Void>>> deleteById(@PathVariable Integer id) {
        return franchiseService.deleteById(id)
                .then(Mono.just(ResponseEntity.ok(
                        ResponseModel.<Void>success(
                                HttpStatus.OK,
                                null,
                                "Franchise deleted successfully"))))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("error", e.getMessage())))));
    }



    @DeleteMapping("/name/{name}")
    public Mono<ResponseEntity<ResponseModel<Void>>> deleteByName(@PathVariable String name) {
        return franchiseService.deleteByName(name)
                .then(Mono.just(ResponseEntity.ok(
                        ResponseModel.<Void>success(
                                HttpStatus.OK,
                                null,
                                "Franchise deleted successfully"))))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("error", e.getMessage())))));
    }
}
