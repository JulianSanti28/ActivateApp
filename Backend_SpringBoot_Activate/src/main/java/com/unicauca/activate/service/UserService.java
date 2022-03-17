/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.unicauca.activate.model.User;
import com.unicauca.activate.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 57322
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        if (validarPassword(user.getPassword())) {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, user.getPassword());
            user.setPassword(hash);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User verificarCredenciales(User user) {
        String query = "FROM User WHERE email = :email";
        List<User> lista = entityManager.createQuery(query, User.class)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, user.getPassword())) {
            return lista.get(0);
        }
        return null;
    }

    @Override
    public boolean validarPassword(String password) {
        // Regex to check valid password.
        String regex = "^(?=.*[1-8])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);

        return m.matches();

    }

}
