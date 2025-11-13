package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Imagenes;
import com.example.aska.repository.ImagenesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenesService {

    @Autowired
    private ImagenesRepository imagenesRepository;

    public List<Imagenes> findAll() {
        return imagenesRepository.findAll();
    }

    public Imagenes findById(Integer id) {
        return imagenesRepository.findById(id).orElseThrow();
    }

    public Imagenes save(Imagenes imagenes) {
        return imagenesRepository.save(imagenes);
    }

    public void deleteById(Integer id) {
        imagenesRepository.deleteById(id);
    }

    public Imagenes patchImagenes(Integer id, Imagenes parcialImagenes) {

        Imagenes listaToUpdate = findById(id);

        if (parcialImagenes.getUrlImagen() != null) {
            listaToUpdate.setUrlImagen(parcialImagenes.getUrlImagen());
        }

        return imagenesRepository.save(listaToUpdate);
    }

}
