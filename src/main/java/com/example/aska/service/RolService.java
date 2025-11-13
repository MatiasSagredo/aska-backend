package com.example.aska.service;

import org.springframework.stereotype.Service;

import com.example.aska.model.Rol;
import com.example.aska.repository.RolRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

     public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Rol findById(Integer id) {
        return rolRepository.findById(id).orElseThrow();
    }

    public Rol save(Rol marca) {
        return rolRepository.save(marca);
    }

    public void deleteById(Integer id) {
        rolRepository.deleteById(id);
    }

    public Rol patchTalla(Integer id, Rol parcialRol) {

        Rol listaToUpdate = findById(id);

        if (parcialRol.getNombreRol() != null) {
            listaToUpdate.setNombreRol(parcialRol.getNombreRol());
        }

        return rolRepository.save(listaToUpdate);
    }
}
