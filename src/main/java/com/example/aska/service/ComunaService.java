package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Comuna;
import com.example.aska.repository.ComunaRepository;
import com.example.aska.repository.DireccionesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private DireccionesRepository direccionesRepository;

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
        // Primero, verificar si el estudiante existe
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("comuna no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        direccionesRepository.deleteByIdComuna(comuna);

        // Finalmente, eliminamos el estudiante
        comunaRepository.delete(comuna);
    }
}
