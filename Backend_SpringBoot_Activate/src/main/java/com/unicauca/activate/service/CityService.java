package com.unicauca.activate.service;

import java.util.Optional;

import com.unicauca.activate.model.City;
import com.unicauca.activate.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
        
    }


    @Override
    public Page<City> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return cityRepository.findAll(pageable);
    }

    @Override
    public Iterable<City> findAll() {
        // TODO Auto-generated method stub
        return cityRepository.findAll();
    }

   

    @Override
    public Optional<City> findById(Long id) {
        // TODO Auto-generated method stub
        return cityRepository.findById(id);
    }

    @Override
    public City save(City city) {
        // TODO Auto-generated method stub
        return cityRepository.save(city);
    }
    
}
