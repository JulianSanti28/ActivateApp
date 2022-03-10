/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.model;

/**
 *
 * @author 57322
 */
public abstract class ClaimHandler {

    //Atributos
    private ClaimHandler nextHandler;
    private String nombre;
    private String apellidos;
    private String email;

    //Constructor
    public ClaimHandler(String nombre, String apellidos, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    //Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public ClaimHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(ClaimHandler handler) {
        nextHandler = handler;
    }

    //Métodos
    public abstract boolean attend(Claim request);
}
