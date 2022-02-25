package com.unicauca.activate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CIU_id")
    private Long CIU_id;

    @Column(length = 50)
    private String CIU_name;

    @OneToMany(mappedBy = "city")
    private List<Event> events;

    public Long getCIU_id() {
        return CIU_id;
    }

    public void setCIU_id(Long cIU_id) {
        CIU_id = cIU_id;
    }

    public String getCIU_name() {
        return CIU_name;
    }

    public void setCIU_name(String cIU_name) {
        CIU_name = cIU_name;
    }

    public void agregarEventos(Event event){
        if (this.events == null) {
            this.events = new ArrayList<>();
            this.events.add(event);
            event.setCity(this);
        } else {
            this.events.add(event);
            event.setCity(this);
        }
    }
}
