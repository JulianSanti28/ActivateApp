/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.service;

import com.unicauca.activate.model.Category;
import com.unicauca.activate.model.Claim;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author 57322
 */
public interface IClaimService {

    public Iterable<Claim> findAll();

    public Page<Claim> findAll(Pageable pageable);

    public Optional<Claim> findById(Long id);

    public Claim save(Claim claim);

    public void deleteById(Long id);

}
