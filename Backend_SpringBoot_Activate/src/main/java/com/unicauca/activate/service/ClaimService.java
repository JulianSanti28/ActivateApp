/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.service;


import com.unicauca.activate.model.Claim;
import com.unicauca.activate.repository.ClaimRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * In this service, you can implement domain logic before of add in the
 * repository
 *
 * @author Julián S Martinez
 */
@Service
public class ClaimService implements IClaimService {

    //Inyección de dependencias
    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public Iterable<Claim> findAll() {
        return claimRepository.findAll();
    }

    @Override
    public Page<Claim> findAll(Pageable pageable) {
        return claimRepository.findAll(pageable);
    }

    @Override
    public Optional<Claim> findById(Long id) {
        return claimRepository.findById(id);
    }

    @Override
    public Claim save(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public void deleteById(Long id) {
        claimRepository.deleteById(id);
    }

}
