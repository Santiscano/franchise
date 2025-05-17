package com.accenture.santiago.franchise.app.branch.service;

import com.accenture.santiago.franchise.app.branch.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    // methods
}
