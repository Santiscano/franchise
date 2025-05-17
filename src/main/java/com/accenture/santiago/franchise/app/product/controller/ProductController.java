package com.accenture.santiago.franchise.app.product.controller;

import com.accenture.santiago.franchise.app.product.dto.SaveProductDto;
import com.accenture.santiago.franchise.app.product.dto.UpdateProductNameDto;
import com.accenture.santiago.franchise.app.product.entity.ProductEntity;
import com.accenture.santiago.franchise.app.product.service.ProductService;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/{id}")
    public Mono<ResponseEntity<ResponseModel<ProductEntity>>> findById(
            @PathVariable Integer id
    ) {
        Mono<ResponseEntity<ResponseModel<ProductEntity>>> res = productService.findById(id)
                .map(product -> ResponseEntity.ok(
                        ResponseModel.success(HttpStatus.OK, product, "Product found successfully")
                ));
        return ResponseUtils.handleMonoResponse(res, "Product not found");
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<ResponseModel<ProductEntity>>> findByName(
            @PathVariable String name
    ) {
        Mono<ResponseEntity<ResponseModel<ProductEntity>>> res = productService.findByName(name)
                .map(product -> ResponseEntity.ok(
                        ResponseModel.success(
                                HttpStatus.OK,
                                product,
                                "Product found successfully"
                        )
                ));
        return ResponseUtils.handleMonoResponse(res, "Product not found");
    }

    @GetMapping
    public Flux<ResponseEntity<ResponseModel<ProductEntity>>> findAll() {
        Flux<ResponseEntity<ResponseModel<ProductEntity>>> res = productService.findAll()
                .map( product -> ResponseEntity.ok(
                        ResponseModel.success(
                                HttpStatus.OK,
                                product,
                                "Products retrieved successfully"
                        )
                ));
        return ResponseUtils.handleFluxResponse(res, "Products not found");
    }

    @PostMapping
    public Mono<ResponseEntity<ResponseModel<ProductEntity>>> saveProduct(
            @Valid @RequestBody SaveProductDto dto
    ) {
        return productService.save(dto)
                .map( prod -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(ResponseModel.success(
                                HttpStatus.CREATED,
                                prod,
                                "Product created successfully"
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
    public Mono<ResponseEntity<ResponseModel<ProductEntity>>> updateProductName(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateProductNameDto dto
    ) {
        Mono<ResponseEntity<ResponseModel<ProductEntity>>> res = productService.updateName(id, dto)
                .map( prod -> ResponseEntity.ok(
                        ResponseModel.success(
                                HttpStatus.OK,
                                prod,
                                "Product updated successfully"
                        )
                ));
        return ResponseUtils.handleMonoResponse(res, "Product not found");
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ResponseModel<Void>>> deleteById(
            @PathVariable Integer id
    ){
        return productService.deleteById(id)
                .then(Mono.just(ResponseEntity.ok(
                        ResponseModel.<Void>success(
                                HttpStatus.OK,
                                null,
                                "Product deleted successfully"
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
    public Mono<ResponseEntity<ResponseModel<Void>>> deleteByName(
            @PathVariable String name
    ) {
        return productService.deleteByName(name)
                .then(Mono.just(ResponseEntity.ok(
                        ResponseModel.<Void>success(
                                HttpStatus.OK,
                                null,
                                "Product deleted successfully"
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
