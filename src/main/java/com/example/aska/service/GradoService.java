package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Grado;
import com.example.aska.repository.GradoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GradoService {

    @Autowired
    private GradoRepository gradoRepository;

    public List<Grado> findAll() {
        return gradoRepository.findAll();
    }

    public Grado findById(Integer id) {
        return gradoRepository.findById(id).orElseThrow();
    }

    public Grado save(Grado grado) {
        return gradoRepository.save(grado);
    }

    public void deleteById(Integer id) {
        gradoRepository.deleteById(id);
    }

    public Grado patchGrado(Integer id, Grado parcialGrado) {

        Grado listaToUpdate = findById(id);

        if (parcialGrado.getNombreGrado() != null) {
            listaToUpdate.setNombreGrado(parcialGrado.getNombreGrado());
        }

        return gradoRepository.save(listaToUpdate);
    }
}
