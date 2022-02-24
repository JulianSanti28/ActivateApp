package com.unicauca.activate.service;

import java.util.Optional;

import com.unicauca.activate.model.City;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface ICityService {
    public Iterable<City> findAll();
    public Page<City> findAll(Pageable pageable);
    public Optional<City> findById(Long id);
    public City save(City city);
    public void deleteById(Long id);
}
