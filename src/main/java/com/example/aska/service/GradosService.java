package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Grados;
import com.example.aska.repository.GradosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GradosService {

    @Autowired
    private GradosRepository gradosRepository;

    public List<Grados> findAll() {
        return gradosRepository.findAll();
    }

    public Grados findById(Integer id) {
        return gradosRepository.findById(id).orElseThrow();
    }

    public Grados save(Grados grados) {
        return gradosRepository.save(grados);
    }

    public void deleteById(Integer id) {
        gradosRepository.deleteById(id);
    }
    // preguntar patch al profe por no tener atributos propios
}
