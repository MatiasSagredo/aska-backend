package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Color;
import com.example.aska.repository.ColorRepository;
import com.example.aska.repository.ColoresRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColoresRepository coloresRepository;

    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    public Color findById(Integer id) {
        return colorRepository.findById(id).orElseThrow();
    }

    public Color save(Color color) {
        return colorRepository.save(color);
    }

    public void deleteById(Integer id) {
        // Primero, verificar si el estudiante existe
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("color no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        coloresRepository.deleteByIdColor(color);

        // Finalmente, eliminamos el estudiante
        colorRepository.delete(color);
    }

    public Color patchColor(Integer id, Color parcialColor) {

        Color listaToUpdate = findById(id);

        if (parcialColor.getNombreColor() != null) {
            listaToUpdate.setNombreColor(parcialColor.getNombreColor());
        }

        return colorRepository.save(listaToUpdate);
    }
}
