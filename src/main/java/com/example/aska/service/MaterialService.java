package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Material;
import com.example.aska.repository.MaterialRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

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
        materialRepository.deleteById(id);
    }

    public Material patchMaterial(Integer id, Material parcialMaterial) {

        Material listaToUpdate = findById(id);

        if (parcialMaterial.getNombreMaterial() != null) {
            listaToUpdate.setNombreMaterial(parcialMaterial.getNombreMaterial());
        }

        return materialRepository.save(listaToUpdate);
    }
}
