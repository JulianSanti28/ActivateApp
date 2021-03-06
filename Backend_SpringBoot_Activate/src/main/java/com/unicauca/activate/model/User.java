/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 57322
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String lastName;
    
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 255, nullable = false)
    private String password;
    
    @Lob
    private byte[] image;

    //private String image;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Event> events;
    
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "user")
    Set<EventUser> assistences;
    
    @JsonIgnore
    @OneToMany(mappedBy="to")
    private List<Follow> followers; //seguidores
    
    @JsonIgnore
    @OneToMany(mappedBy="from")
    private List<Follow> following; // seguidos
    
   // private List<Long> idFollowers;
    //private List<Long> idFollowing;
    //@OneToMany(mappedBy = "userComment")
    //List<Comment> comments;

    public void agregarEventos(Event evento) {
        if (this.events == null) {
            this.events = new ArrayList<>();
            this.events.add(evento);
            evento.setUser(this);
        } else {
            this.events.add(evento);
            evento.setUser(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //public String getImage() {
    //    return image;
    //}

    //public void setImage(String image) {
    //    this.image = image;
    //}
    public void addAsistence(EventUser asistence){
        if (this.assistences == null) {
            this.assistences = new HashSet<>();
            this.assistences.add(asistence);
        }

    }
    public void addFollowFollowing(Follow follow){ // sigo a otra persona
        
        if(this.following==null){
            this.following = new ArrayList<Follow>();
        }
        this.following.add(follow);
    }
    public void addFollowFollower(Follow follow){ // me siguen 
        if(this.followers == null ){
            this.followers = new ArrayList<Follow>();  
        }
      
        this.followers.add(follow);
        
    }
    /*
    public List<Long> getIdsFollowers(){
        List<Long> idFollowers =  new ArrayList<Long>();
        
        for(int j = 0 ; j< this.followers.size();j++){
            idFollowers.add(this.followers.get(j).getFrom().getId());
        }
        return idFollowers;
    }
    public List<Long> getIdsFollowing(){
        List<Long> idFollowing =  new ArrayList<Long>();
        
        for(int j = 0 ; j< this.following.size();j++){
            idFollowing.add(this.following.get(j).getTo().getId());
        }
        return idFollowing;
    } */
    
    public Set<EventUser> getAssistences() {
        return assistences;
    }

    public void setAssistences(Set<EventUser> assistences) {
        this.assistences = assistences;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    public List<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follow> followers) {
        this.followers = followers;
    }

    public List<Follow> getFollowing() {
        return following;
    }

    public void setFollowing(List<Follow> following) {
        this.following = following;
    }
    
    public int getFollowingCount(){
        if(this.following == null){
            return 0;
        }
        return this.following.size();
    }
    public int getFollowerCount(){
        if(this.followers == null){
            return 0;
        }
        return this.followers.size();
    }
    public void delFollowing(long idFollow){
        Follow oldFollow =null;
        for(int i = 0 ; i< this.following.size();i++){
            if(this.following.get(i).getId()==idFollow){
                oldFollow = this.following.get(i);
                this.following.remove(i);
               // this.idFollowing.remove(oldFollow.getTo().getId());
            }
        }
        
    }
    public void delFollower(long idFollow){
        Follow oldFollow =null;
        for(int i = 0 ; i< this.followers.size();i++){
            if(this.followers.get(i).getId()==idFollow){
                oldFollow = this.followers.get(i);
                this.followers.remove(i);
                //this.idFollowers.remove(oldFollow.getFrom().getId());
            }
        }
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "User [assistences=" + assistences + ", comments=" + comments + ", email=" + email + ", events=" + events
                + ", id=" + id + ", lastName=" + lastName + ", name=" + name + ", password=" + password + "]";
    }

    
}
