package com.accenture.santiago.franchise.app.branch.service;

import com.accenture.santiago.franchise.app.branch.dto.UpdateBranchNameDto;
import com.accenture.santiago.franchise.app.branch.dto.SaveBranchDto;
import com.accenture.santiago.franchise.app.branch.entity.BranchEntity;
import com.accenture.santiago.franchise.app.branch.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public Mono<BranchEntity> findById(Integer id) {
        return branchRepository.findById(id);
    }

    @Override
    public Mono<BranchEntity> findByName(String name) {
        return branchRepository.findByName(name);
    }

    @Override
    public Flux<BranchEntity> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public Mono<BranchEntity> save(SaveBranchDto saveBranchDto) {
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setName(saveBranchDto.getNombreSucursal());
        branchEntity.setFranchiseId(saveBranchDto.getFranquiciaId());
        return branchRepository.save(branchEntity);
    }

    @Override
    public Mono<BranchEntity> updateName(Integer id, UpdateBranchNameDto updateBranchNameDto) {
        return findById(id)
                .flatMap(branch -> {
                    branch.setName(updateBranchNameDto.getNombreSucursal());
                    return branchRepository.save(branch);
                });
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return branchRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteByName(String name) {
        return findByName(name)
                .flatMap(branchRepository::delete);
    }
}
