/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.unicauca.activate.repository;

import com.unicauca.activate.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Paula
 */
public interface FollowRepository extends JpaRepository<Follow,Long>{
    
}
