package com.accenture.santiago.franchise.app.branch.repository;

import com.accenture.santiago.franchise.app.branch.entity.BranchEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BranchRepository extends ReactiveCrudRepository<BranchEntity, Integer> { }
