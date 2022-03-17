/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.unicauca.activate.service;

import com.unicauca.activate.model.Follow;
import com.unicauca.activate.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Paula
 */
public interface IFollowService {
    public Iterable<Follow> findAll();

    public Page<Follow> findAll(Pageable pageable);

    public Optional<Follow> findById(Long id);

    public Follow save(Follow follow);

    public void deleteById(Long id);
    
    public Optional<Follow> findByFromAndTo(User from , User to);
    
    
}
