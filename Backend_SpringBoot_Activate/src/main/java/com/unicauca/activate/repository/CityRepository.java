package com.unicauca.activate.repository;
import com.unicauca.activate.model.City;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long>{
    
}
