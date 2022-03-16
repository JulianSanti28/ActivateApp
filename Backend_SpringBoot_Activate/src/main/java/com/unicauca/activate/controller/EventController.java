/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.controller;

import com.unicauca.activate.model.Category;
import com.unicauca.activate.model.City;
import com.unicauca.activate.model.Event;
import com.unicauca.activate.model.EventDTO;
import com.unicauca.activate.model.User;
// import com.unicauca.activate.model.Asistence;
import com.unicauca.activate.service.EventService;
import com.unicauca.activate.service.ICategoryService;
import com.unicauca.activate.service.ICityService;
import com.unicauca.activate.service.IUserService;
import com.unicauca.activate.utilities.JWTUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.unicauca.activate.mapper.Mapper;


/**
 *
 * @author 57322
 */
@RestController
@RequestMapping("/activate/event")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT})
public class EventController {

    

    private Mapper mapper = Mapper.getMapper();

    @Autowired
    private EventService EventService;

    @Autowired
    private IUserService UserService;

    @Autowired
    private JWTUtilities jwUtil;

    @Autowired
    private ICategoryService CategoryService;

    @Autowired
    private ICityService CityService;

    //Crear Evento
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestHeader(value = "Authorization") String token, @RequestBody @Valid EventDTO eventDTO) {

        //System.out.println(jwUtil.getKey(token));
        Event event = mapper.toEvent(eventDTO);

        Long usuarioID = Long.parseLong(jwUtil.getKey(token));
        Optional<User> user = UserService.findById(usuarioID);
        Optional<Category> category = CategoryService.findById(eventDTO.getIdCategory());
        Optional<City> city = CityService.findById(eventDTO.getIdCity());

        city.get().agregarEventos(event);
        category.get().agregarEventos(event);
        user.get().agregarEventos(event);
        System.out.println(event.toString());
        Event save = EventService.save(event);
        return ResponseEntity.ok().body(save);
    }

    //Crear Evento
    @PostMapping("create/image")
    public ResponseEntity<?> createImageEvent(@RequestHeader(value = "event_id") Long eventId, @RequestPart(value = "image", required = false) MultipartFile foto) {
        if (!foto.isEmpty()) {
            String ruta = "./files/imagesEvents";
            try {
                byte[] bytesImage = foto.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + eventId);
                Files.write(rutaAbsoluta, bytesImage);
                //event.setImagen(foto.getOriginalFilename());
            } catch (IOException ex) {
                System.out.println("Error al cargar el archivo");
            }
        }
        Optional<Event> event = EventService.findById(eventId);
        try {
            if (foto.getBytes().length > 0) {
                event.get().setImage(foto.getBytes());
            }
        } catch (IOException ex) {
            System.out.println("Error al guardar bytes");
        }
        Event save = EventService.save(event.get());
        return ResponseEntity.ok().body(save);
    }

    //Obtene por id
    @GetMapping("{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional<Event> oEvent = EventService.findById(id);
        System.out.println("Comentarios" + oEvent.get().getComments().size());
        if (!oEvent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oEvent);
    }

    //Actualizar Evento, recibe Id del evento y los datos a actualizar
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long eventId,@RequestBody EventDTO eventDetails) {
        System.out.println(eventDetails.toString());
        Optional<Event> event = EventService.findById(eventId);
        Optional<Category> category = CategoryService.findById(eventDetails.getIdCategory());
        Optional<City> city = CityService.findById(eventDetails.getIdCity());

        if (!event.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        event.get().setTitulo(eventDetails.getTitulo());
        event.get().setDescripcion(eventDetails.getDescripcion());
        event.get().setUbicacion(eventDetails.getUbicacion());
        event.get().setFecha_inicio(eventDetails.getFecha_inicio());
        event.get().setFecha_final(eventDetails.getFecha_final());
        event.get().setCategory(category.get());
        event.get().setCity(city.get());

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

    //Obtener todos los eventos creados por un usuario
    //Recibe el token del usuario logueado 
    @GetMapping("eventsUser/all/{id}")
    public List<Event> readAllByUser(@PathVariable(value = "id") Long userId) {
        Optional<User> user = UserService.findById(userId);
        List<Event> events = user.get().getEvents();
        return events;
    }
}
