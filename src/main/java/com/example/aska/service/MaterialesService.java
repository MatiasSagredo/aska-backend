package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Materiales;
import com.example.aska.repository.MaterialesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialesService {

    @Autowired
    private MaterialesRepository materialesRepository;

    public List<Materiales> findAll() {
        return materialesRepository.findAll();
    }

    public Materiales findById(Integer id) {
        return materialesRepository.findById(id).orElseThrow();
    }

    public Materiales save(Materiales materiales) {
        return materialesRepository.save(materiales);
    }

    public void deleteById(Integer id) {
        materialesRepository.deleteById(id);
    }
    // preguntar patch al profe por no tener atributos propios
}
