package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Direcciones;
import com.example.aska.repository.DireccionesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionesService {

    @Autowired
    private DireccionesRepository direccionesService;

    public List<Direcciones> findAll() {
        return direccionesService.findAll();
    }

    public Direcciones findById(Integer id) {
        return direccionesService.findById(id).orElseThrow();
    }

    public Direcciones save(Direcciones diciplinas) {
        return direccionesService.save(diciplinas);
    }

    public Direcciones patchDirecciones(Integer id, Direcciones direcciones) {
        Direcciones existing = findById(id);

        if (direcciones.getNombreCalle() != null) {
            existing.setNombreCalle(direcciones.getNombreCalle());
        }

        return direccionesService.save(existing);
    }

    public void deleteById(Integer id) {
        direccionesService.deleteById(id);
    }
}
