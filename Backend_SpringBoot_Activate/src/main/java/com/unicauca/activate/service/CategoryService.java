/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.service;

import com.unicauca.activate.model.Category;
import com.unicauca.activate.repository.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author 57322
 */
@Service
public class CategoryService implements ICategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
       return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category categ) {
        if(this.categoryRepository.findById(categ.getId()).get() == null){
            return categoryRepository.save(categ);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {categoryRepository.deleteById(id);}
    
}
