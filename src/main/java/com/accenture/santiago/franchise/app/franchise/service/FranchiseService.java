package com.accenture.santiago.franchise.app.franchise.service;

import com.accenture.santiago.franchise.app.franchise.dto.SaveFranchiseDto;
import com.accenture.santiago.franchise.app.franchise.dto.UpdateFranchiseNameDto;
import com.accenture.santiago.franchise.app.franchise.entity.FranchiseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseService {

    Mono<FranchiseEntity> findById(Integer id);
    Mono<FranchiseEntity> findByName(String name);
    Flux<FranchiseEntity> findAll();

    Mono<FranchiseEntity> save(SaveFranchiseDto saveFranchiseDto);

    Mono<FranchiseEntity> updateName(Integer id, UpdateFranchiseNameDto dto);

    Mono<Void> deleteById(Integer id);
    Mono<Void> deleteByName(String name);
}
