/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.activate.service;

import com.unicauca.activate.model.Follow;
import com.unicauca.activate.model.User;
import com.unicauca.activate.repository.FollowRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Paula
 */
@Service
public class FollowService implements IFollowService{
    
    @Autowired
    private FollowRepository followRepository;
    
    @Override
    public Iterable<Follow> findAll() {
        return followRepository.findAll();
    }

    @Override
    public Page<Follow> findAll(Pageable pageable) {
        return followRepository.findAll(pageable);
    }

    @Override
    public Optional<Follow> findById(Long id) {
        return followRepository.findById(id);
    }
 
    @Override
    public Follow save(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    public void deleteById(Long id) {
        followRepository.deleteById(id);
    }

    @Override
    public Optional<Follow> findByFromAndTo(User from, User to) {
        return followRepository.findByFromAndTo(from, to);
    }

    
    
}
