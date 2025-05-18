package com.accenture.santiago.franchise.app.product.repository;

import com.accenture.santiago.franchise.app.product.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository  extends ReactiveCrudRepository <ProductEntity, Integer> {
    Mono<ProductEntity> findByName(String name);

    @Query("""
        SELECT DISTINCT ON (p.branch_id)\s
            p.id_product, p.name, p.stock, p.branch_id, b.name
        FROM product p
        JOIN branch b ON b.id_branch = p.branch_id
        WHERE b.franchise_id = :franchiseId
        ORDER BY p.branch_id, p.stock DESC;
    """)
    Flux<ProductEntity> findTopStockProductsByFranchiseId(Integer franchiseId);
}
