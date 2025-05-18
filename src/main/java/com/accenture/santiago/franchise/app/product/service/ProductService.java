package com.accenture.santiago.franchise.app.product.service;

import com.accenture.santiago.franchise.app.product.dto.SaveProductDto;
import com.accenture.santiago.franchise.app.product.dto.UpdateProductNameDto;
import com.accenture.santiago.franchise.app.product.dto.UpdateProductStockDto;
import com.accenture.santiago.franchise.app.product.entity.ProductEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductEntity> findById(Integer id);
    Mono<ProductEntity> findByName(String name);
    Flux<ProductEntity> findAll();

    Mono<ProductEntity> save(SaveProductDto saveProductDto);

    Mono<ProductEntity> updateName(Integer id, UpdateProductNameDto updateProductNameDto);
    Mono<ProductEntity> updateStock(Integer id, UpdateProductStockDto updateProductStockDto);

    Mono<Void> deleteById(Integer id);
    Mono<Void> deleteByName(String name);



    Flux<ProductEntity> reportTopStockProductsByFranchiseId(Integer franchiseId);
}
