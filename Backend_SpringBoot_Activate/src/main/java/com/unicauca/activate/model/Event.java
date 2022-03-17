/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 57322
 */
@Entity
@Table(name = "event")
public class Event {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String titulo = "";

    @Column(length = 50)
    private String descripcion = "";

    @Column(length = 50)
    private String ubicacion = "";

    @Column(length = 50)
    private String fechaInicio = "";

    @Column(length = 50)
    private String fechaFinal = "";

    @Lob
    private byte[] image;

    //Relación 1:N con la entidad category.
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    //Relacion N:1 
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "event") 
    private List<Comment> comments;
    @OneToMany(mappedBy = "event")
    Set<EventUser> assistences;

    @ManyToOne
    @JoinColumn(name = "CIU_id", nullable = false)
    private City city;

    public void agregarComentarios(Comment comment) {
        if (this.comments == null) {
            this.comments = new ArrayList<>();
            this.comments.add(comment);
            comment.setEvent(this);
        } else {
            this.comments.add(comment);
            comment.setEvent(this);
        }
    }
    
    

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<EventUser> getAssistences() {
        return assistences;
    }

    public void setAssistences(Set<EventUser> assistences) {
        this.assistences = assistences;
    }

    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }


    public void setImage(byte[] image) {
        this.image = image;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    

    public String getFechaInicio() {
        return fechaInicio;
    }



    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }



    public String getFechaFinal() {
        return fechaFinal;
    }



    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }



    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void addAsistence(EventUser asistence){
        if (this.assistences == null) {
            this.assistences = new HashSet<>();
            this.assistences.add(asistence);
        }

    }
}
