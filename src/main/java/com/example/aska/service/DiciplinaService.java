package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Diciplina;
import com.example.aska.repository.DiciplinaRepository;
import com.example.aska.repository.DiciplinasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DiciplinaService {

    @Autowired
    private DiciplinaRepository diciplinaRepository;

    @Autowired
    private DiciplinasRepository diciplinasRepository;

    public List<Diciplina> findAll() {
        return diciplinaRepository.findAll();
    }

    public Diciplina findById(Integer id) {
        return diciplinaRepository.findById(id).orElseThrow();
    }

    public Diciplina save(Diciplina diciplina) {
        return diciplinaRepository.save(diciplina);
    }

    public void deleteById(Integer id) {
        // Primero, verificar si el estudiante existe
        Diciplina diciplina = diciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("diciplina no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        diciplinasRepository.deleteByIdDiciplina(diciplina);

        // Finalmente, eliminamos el estudiante
        diciplinaRepository.delete(diciplina);
    }

    public Diciplina patchDiciplina(Integer id, Diciplina parcialDiciplina) {

        Diciplina listaToUpdate = findById(id);

        if (parcialDiciplina.getNombreDiciplina() != null) {
            listaToUpdate.setNombreDiciplina(parcialDiciplina.getNombreDiciplina());
        }

        return diciplinaRepository.save(listaToUpdate);
    }
}
