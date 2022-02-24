/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.mapper;

import com.unicauca.activate.model.Comment;
import com.unicauca.activate.model.CommentDTO;
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
}
