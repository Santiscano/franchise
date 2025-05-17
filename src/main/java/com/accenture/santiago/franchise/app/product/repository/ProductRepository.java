package com.accenture.santiago.franchise.app.product.repository;

import com.accenture.santiago.franchise.app.product.entity.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository  extends ReactiveCrudRepository <ProductEntity, Integer> {
    Mono<ProductEntity> findByName(String name);
}
