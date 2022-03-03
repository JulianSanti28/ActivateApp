/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.activate.controller;

import com.unicauca.activate.mapper.Mapper;
import com.unicauca.activate.model.Comment;
import com.unicauca.activate.model.CommentDTO;
import com.unicauca.activate.model.Event;
import com.unicauca.activate.model.Follow;
import com.unicauca.activate.model.User;
import com.unicauca.activate.model.UserDTO;
import com.unicauca.activate.service.FollowService;
import com.unicauca.activate.service.IUserService;
import com.unicauca.activate.utilities.JWTUtilities;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Paula
 */
@RestController
@RequestMapping("/activate/follow")
public class FollowController {
    
    private Mapper mapper = new Mapper();
    @Autowired
    private FollowService followService;
    
    @Autowired
    private IUserService UserService;
    
    @Autowired
    private JWTUtilities jwUtil;
    
    //crear follow se debe asignar de 1 following 2 ->  y 2 followers 1 
    //le llega token del usuario logeado y como parametro usuario perfil
    @PostMapping("create")
    //public ResponseEntity<?> create(@RequestHeader(value="Authorization") String token, @RequestBody UserDTO p_user) {
    public ResponseEntity<?> create(@RequestHeader(value="Authorization") String token, @RequestBody User p_user) {  
        Long userId_from = Long.parseLong(jwUtil.getKey(token)); //el que hace click en follow 
        //Long userId_to = p_user.getId();
        Long userId_to = p_user.getId();
        Optional<User> user_from = UserService.findById(userId_from);
        Optional<User> user_to =  UserService.findById(userId_to);
        //Comment comment = mapper.toComment(commentDto);
        Follow newFollow = new Follow(user_from.get(),user_to.get());
        //Vinculando las relaciones
        user_from.get().addFollowFollowing(newFollow);
        user_to.get().addFollowFollower(newFollow);
//        User userTo = mapper.toUser(p_user);
//        User userFrom = mapper.toUser()
        Follow save = followService.save(newFollow);
        return ResponseEntity.ok().body(save);
    }
    
    @DeleteMapping("remove/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long followId) {
        if (!followService.findById(followId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Follow oldFollow = followService.findById(followId).get();
        
        oldFollow.getFrom().delFollowing(followId);
        oldFollow.getTo().delFollower(followId);
        
        followService.deleteById(followId);
        return ResponseEntity.ok().build();
    }
    
}
