package com.unicauca.activate.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
@Table(name = "event_user")
public class EventUser {

    @EmbeddedId
    UserEventKey id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    Event event;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    public EventUser() {
    }

    public UserEventKey getId() {
        return id;
    }

    public void setId(UserEventKey id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
