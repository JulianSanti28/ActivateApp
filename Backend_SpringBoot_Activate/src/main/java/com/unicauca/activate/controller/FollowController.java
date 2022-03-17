/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.activate.controller;

import com.unicauca.activate.mapper.Mapper;
import com.unicauca.activate.model.Follow;
import com.unicauca.activate.model.User;
import com.unicauca.activate.service.FollowService;
import com.unicauca.activate.service.IUserService;
import com.unicauca.activate.utilities.JWTUtilities;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Paula
 */
@RestController
@RequestMapping("/activate/follow")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class FollowController {
    
    private Mapper mapper = Mapper.getMapper();
    @Autowired
    private FollowService followService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private JWTUtilities jwUtil;
    
    //crear follow se debe asignar de 1 following 2 ->  y 2 followers 1 
    //le llega token del usuario logeado y como parametro usuario perfil
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestHeader(value="from_user") String fromUser, @RequestHeader(value="to_user") String toUser) {
   

        Optional<User> userFrom = userService.findById(Long.parseLong(fromUser));
        Optional<User> userTo =  userService.findById(Long.parseLong(toUser));

        Follow newFollow = new Follow(userFrom.get(),userTo.get());
        //Vinculando las relaciones
        userFrom.get().addFollowFollowing(newFollow);
        userTo.get().addFollowFollower(newFollow);

        Follow save = followService.save(newFollow);
        return ResponseEntity.ok().body(save);
    }
    
    @DeleteMapping("remove/{id}")
   // public ResponseEntity<?> delete(@PathVariable(value = "id") Long followId) {
    //@DeleteMapping("remove")
    public ResponseEntity<?> delete(@RequestHeader(value = "Authorization") String token, @PathVariable(value = "id")Long toUser) {
        Long fromUser = Long.parseLong(jwUtil.getKey(token));
        //Long toUser = Long.parseLong(to_user);
        
        
        Optional<User> userFrom = userService.findById(fromUser);
        Optional<User> userTo =  userService.findById(toUser);
        Follow oldFollow = followService.findByFromAndTo(userFrom.get(), userTo.get()).get();
        
        if (!followService.findByFromAndTo(userFrom.get(), userTo.get()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        
        oldFollow.getFrom().delFollowing(oldFollow.getId());
        oldFollow.getTo().delFollower(oldFollow.getId());
        
        followService.deleteById(oldFollow.getId());
        return ResponseEntity.ok().build();
    }
    
}
