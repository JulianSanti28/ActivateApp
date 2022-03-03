
package com.unicauca.activate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="from_user_fk")
    private User from;

    @ManyToOne
    @JoinColumn(name="to_user_fk")
    private User to;

    public Follow() {};
    
    public Follow(User from, User to) {
        this.from = from;
        this.to = to;
    }

    public long getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }
    
    
}
