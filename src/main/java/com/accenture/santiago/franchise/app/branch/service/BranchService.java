package com.accenture.santiago.franchise.app.branch.service;

import com.accenture.santiago.franchise.app.branch.dto.UpdateBranchNameDto;
import com.accenture.santiago.franchise.app.branch.dto.SaveBranchDto;
import com.accenture.santiago.franchise.app.branch.entity.BranchEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BranchService {

    Mono<BranchEntity> findById(Integer id);
    Mono<BranchEntity> findByName(String name);
    Flux<BranchEntity> findAll();

    Mono<BranchEntity> save(SaveBranchDto saveBranchDto);

    Mono<BranchEntity> updateName(Integer id, UpdateBranchNameDto updateBranchNameDto);

    Mono<Void> deleteById(Integer id);
    Mono<Void> deleteByName(String name);
}
