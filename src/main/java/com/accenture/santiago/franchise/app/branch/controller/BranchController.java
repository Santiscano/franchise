package com.accenture.santiago.franchise.app.branch.controller;

import com.accenture.santiago.franchise.app.branch.dto.UpdateBranchNameDto;
import com.accenture.santiago.franchise.app.branch.dto.SaveBranchDto;
import com.accenture.santiago.franchise.app.branch.entity.BranchEntity;
import com.accenture.santiago.franchise.app.branch.service.BranchService;
import com.accenture.santiago.franchise.handleResponse.ResponseModel;
import com.accenture.santiago.franchise.utils.ResponseUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ResponseModel<BranchEntity>>> findById(@PathVariable Integer id) {
        Mono<ResponseEntity<ResponseModel<BranchEntity>>> res = branchService.findById(id)
                .map(branch -> ResponseEntity.ok(
                        ResponseModel.success( HttpStatus.OK, branch, "Branch found successfully" )
                ));
        return ResponseUtils.handleMonoResponse(res, "Branch not found");
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<ResponseModel<BranchEntity>>> findByName(@PathVariable String name) {
        Mono<ResponseEntity<ResponseModel<BranchEntity>>> res = branchService.findByName(name)
                .map( branch -> ResponseEntity.ok(
                        ResponseModel.success(HttpStatus.OK, branch, "Branch found successfully")
                ));
        return ResponseUtils.handleMonoResponse(res, "Branch not found");
    }

    @GetMapping
    public Flux<ResponseEntity<ResponseModel<BranchEntity>>> findAll() {
        Flux<ResponseEntity<ResponseModel<BranchEntity>>> res = branchService.findAll()
                .map(branch -> ResponseEntity.ok(
                        ResponseModel.success(
                                HttpStatus.OK,
                                branch,
                                "Branches retrieved successfully"
                        )
                ));
        return ResponseUtils.handleFluxResponse(res, "Branches not found");
    }

    @PostMapping
    public Mono<ResponseEntity<ResponseModel<BranchEntity>>> saveBranch(@Valid @RequestBody SaveBranchDto dto) {
        return branchService.save(dto)
                .map( branch -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(ResponseModel.success(
                                HttpStatus.CREATED,
                                branch,
                                "Branch created successfully"
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
    public Mono<ResponseEntity<ResponseModel<BranchEntity>>> updateBranchName(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateBranchNameDto dto) {
        Mono<ResponseEntity<ResponseModel<BranchEntity>>> res = branchService.updateName(id, dto)
                .map( branch -> ResponseEntity.ok(
                        ResponseModel.success(
                                HttpStatus.OK,
                                branch,
                                "Branch updated succesfully"
                        )
                ));
        return ResponseUtils.handleMonoResponse(res, "Branch not found");
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ResponseModel<Void>>> deleteById(@PathVariable Integer id) {
        return branchService.deleteById(id)
                .then(Mono.just(ResponseEntity.ok(
                        ResponseModel.<Void>success(
                                HttpStatus.OK,
                                null,
                                "Branch deleted successfully"
                        )
                )))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                Map.of("error", e.getMessage()))
                        )
                ));
    }

    @DeleteMapping("/name/{name}")
    public Mono<ResponseEntity<ResponseModel<Void>>> deleteByName(@PathVariable String name) {
        return branchService.deleteByName(name)
                .then(Mono.just(ResponseEntity.ok(
                        ResponseModel.<Void>success(
                                HttpStatus.OK,
                                null,
                                "Branch deleted successfully"
                        )
                )))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseModel.error(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                Map.of("error", e.getMessage()))
                        )
                ));
    }
}
