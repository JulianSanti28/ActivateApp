package com.unicauca.activate.controller;

import java.util.Optional;

import com.unicauca.activate.model.Asistence;
import com.unicauca.activate.model.Event;
import com.unicauca.activate.model.EventUser;
import com.unicauca.activate.model.User;
import com.unicauca.activate.model.UserEventKey;
import com.unicauca.activate.service.EventService;
import com.unicauca.activate.service.IEventUserService;
import com.unicauca.activate.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activate/assist")

public class EventUserController {
    
    @Autowired
    private IEventUserService EventUserService;

    @Autowired
    private IUserService UserService;

    @Autowired
    private EventService EventService;

    //Crear Asistencia
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Asistence asistence) {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(asistence.toString());
        Optional<User> user = UserService.findById(asistence.getIdentificacionUsuario());
        Optional<Event> event = EventService.findById(asistence.getIdentificacionEvento());

        UserEventKey userEventKey = new UserEventKey();
        
        userEventKey.setEventId(asistence.getIdentificacionEvento());
        userEventKey.setUserId(asistence.getIdentificacionUsuario());

        EventUser eventUser = new EventUser();
        eventUser.setId(userEventKey);
        eventUser.setEvent(event.get());
        eventUser.setUser(user.get());
        
        EventUser save = EventUserService.save(eventUser);
        
        return ResponseEntity.ok().body(save);
    }
}
