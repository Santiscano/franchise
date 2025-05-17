package com.accenture.santiago.franchise.app.branch.repository;

import com.accenture.santiago.franchise.app.branch.entity.BranchEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BranchRepository extends ReactiveCrudRepository<BranchEntity, Integer> {
    Mono<BranchEntity> findByName(String name);
}
