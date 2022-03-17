/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.model;

import com.unicauca.activate.utilities.ScoreEnum;
import static com.unicauca.activate.utilities.ScoreEnum.UNO;
import java.util.Date;

/**
 *
 * @author 57322
 */
public class CommentDTO {
    
    //Atributo
    private String descripcion;
    private String fechaComentario;
    private int  score;
    private Long eventId;
    
    
    
    //Getters and Setters
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
