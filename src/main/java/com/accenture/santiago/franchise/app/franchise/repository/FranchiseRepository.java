package com.accenture.santiago.franchise.app.franchise.repository;

import com.accenture.santiago.franchise.app.franchise.entity.FranchiseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface FranchiseRepository extends ReactiveCrudRepository<FranchiseEntity, Integer> {
    Mono<FranchiseEntity> findByName(String name);
}
