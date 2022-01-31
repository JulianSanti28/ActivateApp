package com.unicauca.activate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unicauca.activate.model.User;

/**
 * Interface respositorio del modelo User
 *
*/
public interface UserRepository extends JpaRepository<User, Long> {

}
