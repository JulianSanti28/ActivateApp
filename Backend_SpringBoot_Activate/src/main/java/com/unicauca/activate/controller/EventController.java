/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.controller;

import com.unicauca.activate.model.Event;
import com.unicauca.activate.model.User;
// import com.unicauca.activate.model.Asistence;
import com.unicauca.activate.service.EventService;
import com.unicauca.activate.service.IUserService;
import com.unicauca.activate.utilities.JWTUtilities;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 57322
 */
@RestController
@RequestMapping("/activate/event")
public class EventController {

    @Autowired
    private EventService EventService;
    
    @Autowired
    private IUserService UserService;

    @Autowired
    private JWTUtilities jwUtil;

    //Crear Evento
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestHeader(value="Authorization") String token, @RequestBody Event event){  
        Long usuarioID = Long.parseLong(jwUtil.getKey(token));    
        Optional<User> user =  UserService.findById(usuarioID); 
        user.get().agregarEventos(event);
        Event save = EventService.save(event);
        return ResponseEntity.ok().body(save);
    }

    //Obtene por id
    @GetMapping("{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional<Event> oEvent = EventService.findById(id);
        if (!oEvent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oEvent);
    }

    //Actualizar Evento
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody Event eventDetails, @PathVariable(value = "id") Long eventId) {
        Optional<Event> event = EventService.findById(eventId);

        if (!event.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        event.get().setTitulo(eventDetails.getTitulo());
        event.get().setDescripcion(eventDetails.getDescripcion());
        event.get().setUbicacion(eventDetails.getUbicacion());
        event.get().setFecha_inicio(eventDetails.getFecha_inicio());
        event.get().setFecha_final(eventDetails.getFecha_final());

        return ResponseEntity.status(HttpStatus.CREATED).body(EventService.save(event.get()));
    }

    //Borrar Usuario
    @DeleteMapping("remove/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long eventId) {
        if (!EventService.findById(eventId).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        EventService.deleteById(eventId);
        return ResponseEntity.ok().build();
    }

    //Obtener todos los usuarios
    @GetMapping("events/all")
    public List<Event> readAll() {
        List<Event> events = StreamSupport
                .stream(EventService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return events;
    }

}
