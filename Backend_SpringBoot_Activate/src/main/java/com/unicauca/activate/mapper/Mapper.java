/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.mapper;

import com.unicauca.activate.model.Comment;
import com.unicauca.activate.model.CommentDTO;
import com.unicauca.activate.model.Event;
import com.unicauca.activate.model.EventDTO;

import org.springframework.stereotype.Component;


/**
 *
 * @author 57322
 */
@Component
public class Mapper {
    
    /**
     * Convetir de CommentDTO a Comment
     * @param commentDto
     * @return 
     */
    public Comment toComment(CommentDTO commentDto){
        Comment comment = new Comment();
        comment.setDescripcion(commentDto.getDescripcion());
        comment.setFechaComentario(commentDto.getFechaComentario());
        comment.setScore(commentDto.getScore());
        return comment;
    }

    public Event toEvent(EventDTO eventDto){
        Event event = new Event();
        event.setTitulo(eventDto.getTitulo());
        event.setDescripcion(eventDto.getDescripcion());
        event.setUbicacion(eventDto.getUbicacion());
        event.setFecha_inicio(eventDto.getFecha_inicio());
        event.setFecha_final(eventDto.getFecha_final());
        return event;
    }
    
   
}
