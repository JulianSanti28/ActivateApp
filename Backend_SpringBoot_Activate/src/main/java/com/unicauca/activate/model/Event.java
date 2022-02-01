/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.model;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    private String fecha_inicio = "";
    
    @Column(length = 50)
    private String fecha_final = "";
    
    //Atributo del Modelo
   // private Long user_id;
    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "asistencia",joinColumns = {@JoinColumn(name = "event_id")},
                                    inverseJoinColumns ={@JoinColumn(name = "user_id_asocciated")})
    private Set<User> usuarios = new HashSet<>();
    
    public Set<User> getUsers() {
        return usuarios;
    }

    public void setUsers(Set<User> usuarios) {
        this.usuarios = usuarios;
    }

    private Long user_id_;

    public Long getUser_id_() {
        return user_id_;
    }

    public void setUser_id_(Long user_id_) {
        this.user_id_ = user_id_;
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

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    @Override
    public String toString() {
        return "Event [descripcion=" + descripcion + ", fecha_final=" + fecha_final + ", fecha_inicio=" + fecha_inicio
                + ", id=" + id + ", titulo=" + titulo + ", ubicacion=" + ubicacion + ", user=" + user + ", user_id_="
                + user_id_ + ", users=" + usuarios + "]";
    }

    

}
