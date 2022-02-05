/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.service;

import com.unicauca.activate.model.Category;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author 57322
 */
public interface ICategoryService {
    
    public Iterable<Category> findAll();
    public Page<Category> findAll(Pageable pageable);
    public Optional<Category> findById(Long id);
    public Category save(Category categ);
    public void deleteById(Long id);
    
}
