package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Comuna;
import com.example.aska.repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }

    public Comuna findById(Integer id) {
        return comunaRepository.findById(id).orElseThrow();
    }

    public Comuna save(Comuna diciplinas) {
        return comunaRepository.save(diciplinas);
    }

    public Comuna patchComuna(Integer id, Comuna comuna) {
        Comuna existingComuna = comunaRepository.findById(comuna.getIdComuna()).orElseThrow();

        if (comuna.getNombreComuna() != null) {
            existingComuna.setNombreComuna(comuna.getNombreComuna());
        }

        return comunaRepository.save(existingComuna);
    }

    public void deleteById(Integer id) {
        comunaRepository.deleteById(id);
    }
}
