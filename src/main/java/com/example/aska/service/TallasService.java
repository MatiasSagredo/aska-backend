package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Tallas;
import com.example.aska.repository.TallasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TallasService {

    @Autowired
    private TallasRepository tallasRepository;

    public List<Tallas> findAll() {
        return tallasRepository.findAll();
    }

    public Tallas findById(Integer id) {
        return tallasRepository.findById(id).orElseThrow();
    }

    public Tallas save(Tallas tallas) {
        return tallasRepository.save(tallas);
    }

    public void deleteById(Integer id) {
        tallasRepository.deleteById(id);
    }
    // preguntar patch al profe por no tener atributos propios
}
