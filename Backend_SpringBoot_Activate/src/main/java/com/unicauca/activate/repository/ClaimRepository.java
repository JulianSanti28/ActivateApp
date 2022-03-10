/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.repository;

import com.unicauca.activate.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 57322
 */
@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    
}
