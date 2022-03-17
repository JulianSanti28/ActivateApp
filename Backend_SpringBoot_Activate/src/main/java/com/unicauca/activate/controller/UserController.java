package com.unicauca.activate.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.unicauca.activate.model.Event;
import com.unicauca.activate.model.User;
import com.unicauca.activate.service.IUserService;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unicauca.activate.model.Follow;
import com.unicauca.activate.utilities.JWTUtilities;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.ArrayList;

/**
 * Clase controladora de usuario
 */
@RestController
@RequestMapping("/activate")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JWTUtilities jwUtil;

    //Crear Usuario
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody User user) {
        User save = userService.save(user);
        return ResponseEntity.ok().body(save);
    }

    //Leer Usuario
    @GetMapping("user/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional<User> oUser = userService.findById(id);
        if (!oUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oUser);
    }

    //Actualizar Usuario
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value = "id") Long userId) {
        Optional<User> user = userService.findById(userId);

        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        user.get().setName(userDetails.getName());
        user.get().setLastName(userDetails.getLastName());
        user.get().setEmail(userDetails.getEmail());
        user.get().setPassword(userDetails.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
    }

    //Borrar Usuario
    @DeleteMapping("remove/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {
        if (!userService.findById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("users/all")
    public List<User> readAll() {
        List<User> users = StreamSupport
                .stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }

    @GetMapping("user/follow/{id}")
    public boolean buscarSeguidor(@RequestHeader(value = "Authorization") String token, @PathVariable(value = "id") Long userId) {
        //Long usuarioID = UserService.findById(Long.parseLong(fromUser)).get().getId(); //token usuario logueado
        Long usuarioID = Long.parseLong(jwUtil.getKey(token));
        Optional<User> user = userService.findById(userId); // perfil user visitado
        List<Follow> lista = user.get().getFollowers();
        for (int j = 0; j < lista.size(); j++) {
            if (lista.get(j).getFrom().getId() == usuarioID) {
                return true;
            }
        }
        return false;
    }

    @PostMapping("create/image")
    public ResponseEntity<?> createImageUser(@RequestHeader(value = "user_id") Long userId, @RequestPart(value = "image", required = false) MultipartFile foto) {
        if (!foto.isEmpty()) {
            String ruta = "./files/imagesUsers";
            try {
                byte[] bytesImage = foto.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + userId);
                Files.write(rutaAbsoluta, bytesImage);
                //event.setImagen(foto.getOriginalFilename());
            } catch (IOException ex) {
                System.out.println("Error al cargar el archivo");
            }
        }
        Optional<User> user = userService.findById(userId);
        try {
            if (foto.getBytes().length > 0) {
                user.get().setImage(foto.getBytes());
            }
        } catch (IOException ex) {
        }
        User save = userService.save(user.get());
        return ResponseEntity.ok().body(save);
    }


    @GetMapping("authentication/creationEvent/{id}")
    public boolean authenticateEventCreation(@RequestHeader(value = "Authorization") String token, @PathVariable(value = "id") Long eventId) {
        Long userId = Long.parseLong(jwUtil.getKey(token));
        Optional<User> user = userService.findById(userId);
        List<Event> events = user.get().getEvents();

        for (Event temp : events) {
            if (temp.getId() == eventId) {
                return true;
            }
        }
        return false;
    }
}
