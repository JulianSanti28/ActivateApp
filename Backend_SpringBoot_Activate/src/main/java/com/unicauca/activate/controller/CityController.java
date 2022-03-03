package com.unicauca.activate.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.unicauca.activate.service.ICityService;
import com.unicauca.activate.model.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/activate/city")
public class CityController {

    @Autowired
    private ICityService CityService;

    //Obtener todos los usuarios
    @GetMapping("cities/all")
    public List<City> readAll() {
        List<City> users = StreamSupport
                .stream(CityService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }

}
