package com.example.myhouse.service;

import com.example.myhouse.model.Myhouse;
import com.example.myhouse.repository.MyhouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyhouseServiceImpl implements GeneralService<Myhouse> {
    @Autowired
    MyhouseRepository myhouseRepository;
    @Override
    public List<Myhouse> findAll() {
        return myhouseRepository.findAll();
    }

    @Override
    public Page<Myhouse> findAll(Pageable pageable) {
        return myhouseRepository.findAll(pageable);
    }

    @Override
    public Optional<Myhouse> findById(int id) {
        return myhouseRepository.findById(id);
    }

    @Override
    public Myhouse save(Myhouse myhouse) {
        return myhouseRepository.save(myhouse);
    }

    @Override
    public void remove(int id) {
    myhouseRepository.deleteById(id);
    }

    @Override
    public List<Myhouse> findAllByNameContaining(String name) {
        return myhouseRepository.findAllByNameContaining(name);
    }



}
