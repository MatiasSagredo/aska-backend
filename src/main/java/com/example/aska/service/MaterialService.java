package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Material;
import com.example.aska.repository.MaterialRepository;
import com.example.aska.repository.MaterialesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialesRepository materialesRepository;

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Material findById(Integer id) {
        return materialRepository.findById(id).orElseThrow();
    }

    public Material save(Material marca) {
        return materialRepository.save(marca);
    }

    public void deleteById(Integer id) {
        // Primero, verificar si el estudiante existe
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("material no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        materialesRepository.deleteByIdMaterial(material);

        // Finalmente, eliminamos el estudiante
        materialRepository.delete(material);
    }

    public Material patchMaterial(Integer id, Material parcialMaterial) {

        Material listaToUpdate = findById(id);

        if (parcialMaterial.getNombreMaterial() != null) {
            listaToUpdate.setNombreMaterial(parcialMaterial.getNombreMaterial());
        }

        return materialRepository.save(listaToUpdate);
    }
}
