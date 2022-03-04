/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.controller;


import javax.persistence.Entity;

import com.unicauca.activate.mapper.JsonResponseLogin;
import com.unicauca.activate.model.User;
import com.unicauca.activate.service.UserService;
import com.unicauca.activate.utilities.JWTUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/activate")
public class LoginController {
    @Autowired
    private UserService UserService;
    @Autowired
    private JWTUtilities jwtUtil;
    //Crear Usuario
    @PostMapping("login")
    public ResponseEntity<?>  login(@RequestBody User user){
        User usuarioLogueado = UserService.verificarCredenciales(user);
        if (usuarioLogueado != null) {
            
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            JsonResponseLogin userLogin = new JsonResponseLogin();
            userLogin.setToken(tokenJwt);
            userLogin.setUser(usuarioLogueado);
            
            return ResponseEntity.ok().body(userLogin);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);   
    }
 
}