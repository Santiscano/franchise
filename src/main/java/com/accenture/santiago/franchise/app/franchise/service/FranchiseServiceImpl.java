package com.accenture.santiago.franchise.app.franchise.service;

import com.accenture.santiago.franchise.app.franchise.dto.SaveFranchiseDto;
import com.accenture.santiago.franchise.app.franchise.dto.UpdateFranchiseNameDto;
import com.accenture.santiago.franchise.app.franchise.entity.FranchiseEntity;
import com.accenture.santiago.franchise.app.franchise.mapper.FranchiseMapper;
import com.accenture.santiago.franchise.app.franchise.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    //private final FranchiseMapper mapper;

    @Override
    public Mono<FranchiseEntity> findById(Integer id) {
        return franchiseRepository.findById(id);
    }

    @Override
    public Mono<FranchiseEntity> findByName(String name) {
        return franchiseRepository.findByName(name);
    }

    @Override
    public Flux<FranchiseEntity> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Mono<FranchiseEntity> save(SaveFranchiseDto saveFranchiseDto) {
        //FranchiseEntity franchiseEntity = mapper.toEntitySave(saveFranchiseDto);
        //return franchiseRepository.save(franchiseEntity);

        FranchiseEntity franchiseEntity = new FranchiseEntity();
        franchiseEntity.setName(saveFranchiseDto.getNombreFranquicia());
        return franchiseRepository.save(franchiseEntity);
    }

    @Override
    public Mono<FranchiseEntity> updateName(Integer id, UpdateFranchiseNameDto updateFranchiseNameDto) {
        return findById(id)
                .flatMap(franchise -> {
                    franchise.setName(updateFranchiseNameDto.getNombreFranquicia());
                    return franchiseRepository.save(franchise);
                });
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return franchiseRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteByName(String name) {
        return findByName(name)
                .flatMap(franchiseRepository::delete);
    }
}
