package com.unicauca.activate.controller;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.unicauca.activate.model.Category;
import com.unicauca.activate.service.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/activate/category")
public class CategoryController {
    
    @Autowired
    private ICategoryService CategoryService;

    //Obtener todos los usuarios
    @GetMapping("categories/all")
    public List<Category> readAll() {
        List<Category> users = StreamSupport
                .stream(CategoryService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }
}
