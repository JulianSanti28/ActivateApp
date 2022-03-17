/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.mapper;

import com.unicauca.activate.model.Claim;
import com.unicauca.activate.model.ClaimDTO;
import com.unicauca.activate.model.ClaimType;
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
public final class Mapper {

    /**
     * Convetir de CommentDTO a Comment
     *
     * @param commentDto
     * @return
     */
    private static Mapper  instance;
    
    private Mapper() {
    
    }
    
    
    public static Mapper getMapper(){
        
        if(instance==null){
            instance = new Mapper();
        }
        return instance;
    }

    public Comment toComment(CommentDTO commentDto) {
        Comment comment = new Comment();
        comment.setDescripcion(commentDto.getDescripcion());
        comment.setFechaComentario(commentDto.getFechaComentario());
        comment.setScore(commentDto.getScore());
        return comment;
    }

    public Event toEvent(EventDTO eventDto) {
        Event event = new Event();
        event.setTitulo(eventDto.getTitulo());
        event.setDescripcion(eventDto.getDescripcion());
        event.setUbicacion(eventDto.getUbicacion());
        event.setFechaInicio(eventDto.getFechaInicio());
        event.setFechaFinal(eventDto.getFechaFinal());
        return event;
    }

    public Claim toClaim(ClaimDTO claimDto) {
        Claim claim = new Claim(claimDto.getTitle(), claimDto.getDescription());
        String typeClaim = claimDto.getType().toUpperCase();
        switch (typeClaim) {
            case "BASICA":
                claim.setType(ClaimType.BASICA);
                break;
            case "MEDIA":
                claim.setType(ClaimType.MEDIA);
                break;
            case "ALTA":
                claim.setType(ClaimType.ALTA);
                break;
            case "INMEDIATA":
                claim.setType(ClaimType.INMEDIATA);
                break;
            default:
                claim.setType(ClaimType.UNCLASSIFIED);
        }
        return claim;
    }
}
