/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.controller;

import com.unicauca.activate.mapper.Mapper;
import com.unicauca.activate.model.Comment;
import com.unicauca.activate.model.CommentDTO;
import com.unicauca.activate.model.Event;
import com.unicauca.activate.model.User;
import com.unicauca.activate.service.CommentService;
import com.unicauca.activate.service.EventService;
import com.unicauca.activate.service.IUserService;
import com.unicauca.activate.utilities.JWTUtilities;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 57322
 */
@RestController
@RequestMapping("/activate/comment")
public class CommentController {

    private Mapper mapper = Mapper.getMapper();
    @Autowired
    private EventService EventService;

    @Autowired
    private CommentService CommentService;

    @Autowired
    private IUserService UserService;

    @Autowired
    private JWTUtilities jwUtil;

    //Crear Commentario
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestHeader(value="Authorization") String token, @RequestBody CommentDTO commentDto) {
        Long userId = Long.parseLong(jwUtil.getKey(token)); 
        Long eventId = commentDto.getEventId();
        Optional<User> user = UserService.findById(userId);
        Optional<Event> event = EventService.findById(eventId);
        Comment comment = mapper.toComment(commentDto);
        //Vinculando las relaciones
        comment.setUser(user.get());
        event.get().agregarComentarios(comment);
        Comment save = CommentService.save(comment);
        return ResponseEntity.ok().body(save);
    }

}
