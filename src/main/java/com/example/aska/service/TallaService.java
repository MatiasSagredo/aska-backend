package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Talla;
import com.example.aska.repository.TallaRepository;
import com.example.aska.repository.TallasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TallaService {

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private TallasRepository tallasRepository;

    public List<Talla> findAll() {
        return tallaRepository.findAll();
    }

    public Talla findById(Integer id) {
        return tallaRepository.findById(id).orElseThrow();
    }

    public Talla save(Talla marca) {
        return tallaRepository.save(marca);
    }

    public void deleteById(Integer id) {
        // Primero, verificar si el estudiante existe
        Talla talla = tallaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("talla no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        tallasRepository.deleteByIdTalla(talla);

        // Finalmente, eliminamos el estudiante
        tallaRepository.delete(talla);
    }

    public Talla patchTalla(Integer id, Talla parcialTalla) {

        Talla listaToUpdate = findById(id);

        if (parcialTalla.getNombreTalla() != null) {
            listaToUpdate.setNombreTalla(parcialTalla.getNombreTalla());
        }

        return tallaRepository.save(listaToUpdate);
    }
}
