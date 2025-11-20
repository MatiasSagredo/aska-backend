package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Colores;
import com.example.aska.repository.ColoresRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColoresService {

    @Autowired
    private ColoresRepository coloresRepository;

    @Autowired

    public List<Colores> findAll() {
        return coloresRepository.findAll();
    }

    public Colores findById(Integer id) {
        return coloresRepository.findById(id).orElseThrow();
    }

    public Colores save(Colores color) {
        return coloresRepository.save(color);
    }

    public void deleteById(Integer id) {
        coloresRepository.deleteById(id);
    }
}
