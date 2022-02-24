package com.unicauca.activate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserEventKey implements Serializable {
    @Column(name = "event_id")
    Long eventId;

    @Column(name = "user_id")
    Long userId;

    

    public Long getEventId() {
        return eventId;
    }



    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }



    public Long getUserId() {
        return userId;
    }



    public void setUserId(Long userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "UserEventKey [eventId=" + eventId + ", userId=" + userId + "]";
    }

    
}
