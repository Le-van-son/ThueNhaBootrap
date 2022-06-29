package com.example.myhouse.repository;

import com.example.myhouse.model.Myhouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyhouseRepository extends JpaRepository<Myhouse,Integer> {
    List<Myhouse> findAllByNameContaining(String name);

}
