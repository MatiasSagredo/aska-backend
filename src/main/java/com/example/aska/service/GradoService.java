package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Grado;
import com.example.aska.repository.GradoRepository;
import com.example.aska.repository.GradosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GradoService {

    @Autowired
    private GradoRepository gradoRepository;

    @Autowired
    private GradosRepository gradosRepository;

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
        // Primero, verificar si el estudiante existe
        Grado grado = gradoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("grado no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        gradosRepository.deleteByIdGrado(grado);

        // Finalmente, eliminamos el estudiante
        gradoRepository.delete(grado);
    }

    public Grado patchGrado(Integer id, Grado parcialGrado) {

        Grado listaToUpdate = findById(id);

        if (parcialGrado.getNombreGrado() != null) {
            listaToUpdate.setNombreGrado(parcialGrado.getNombreGrado());
        }

        return gradoRepository.save(listaToUpdate);
    }
}
