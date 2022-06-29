package com.example.myhouse.service;

import com.example.myhouse.model.Myhouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T> {
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    Optional<T> findById(int id);

    Myhouse save(T t);

    void remove(int id);

    List<Myhouse>findAllByNameContaining(String name);
}
