/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.service;

import com.unicauca.activate.model.Comment;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author 57322
 */
public interface ICommentService {

    public Iterable<Comment> findAll();

    public Page<Comment> findAll(Pageable pageable);

    public Optional<Comment> findById(Long id);

    public Comment save(Comment comment);

    public void deleteById(Long id);

}
