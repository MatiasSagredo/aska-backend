package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Region;
import com.example.aska.repository.ComunaRepository;
import com.example.aska.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Integer id) {
        return regionRepository.findById(id).orElseThrow();
    }

    public Region save(Region diciplinas) {
        return regionRepository.save(diciplinas);
    }

    public void deleteById(Integer id) {
        // Primero, verificar si el estudiante existe
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        comunaRepository.deleteByIdRegion(region);

        // Finalmente, eliminamos el estudiante
        regionRepository.delete(region);
    }

    public Region patchRegion(Integer id, Region parcialRegion) {
        Region existingRegion = findById(id);

        if (existingRegion != null) {
            if (parcialRegion.getNombreRegion() != null) {
                existingRegion.setNombreRegion(parcialRegion.getNombreRegion());
            }
            return regionRepository.save(existingRegion);
        }
        return null;
    }
}
