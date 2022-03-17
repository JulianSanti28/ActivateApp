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
public class City{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CIU_id")
    private Long ciuId;

    @Column(length = 50)
    private String ciuName;

    @OneToMany(mappedBy = "city")
    private List<Event> events;

    public Long getCiuId() {
        return ciuId;
    }

    public void setCiuId(Long ciuId) {
        this.ciuId = ciuId;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getCiuName() {
        return ciuName;
    }

    public void setCiuName(String ciuName) {
        this.ciuName = ciuName;
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
