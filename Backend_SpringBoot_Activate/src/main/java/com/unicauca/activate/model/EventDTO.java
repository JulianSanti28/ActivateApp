package com.unicauca.activate.model;

public class EventDTO {
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private String fechaInicio;
    private String fechaFinal;
    private Long idCategory;
    private Long idUser;
    private Long idCity;

    public EventDTO(){
        
    }
    
    

    public EventDTO(String titulo, String descripcion, String ubicacion, String fechaInicio, String fechaFinal,
            Long idCategory, Long idUser, Long idCity) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.idCategory = idCategory;
        this.idUser = idUser;
        this.idCity = idCity;
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


    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    


}
