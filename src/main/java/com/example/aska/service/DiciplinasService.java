package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Diciplinas;
import com.example.aska.repository.DiciplinasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DiciplinasService {

    @Autowired
    private DiciplinasRepository diciplinasRepository;

    public List<Diciplinas> findAll() {
        return diciplinasRepository.findAll();
    }

    public Diciplinas findById(Integer id) {
        return diciplinasRepository.findById(id).orElseThrow();
    }

    public Diciplinas save(Diciplinas diciplinas) {
        return diciplinasRepository.save(diciplinas);
    }

    public void deleteById(Integer id) {
        diciplinasRepository.deleteById(id);
    }
    // preguntar patch al profe por no tener atributos propios
}
