package com.example.aska.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aska.model.Imagenes;
import com.example.aska.service.ImagenesService;

@RestController
@RequestMapping("/api/v1/Imagenes")
public class ImagenesController {

    @Autowired
    private ImagenesService imagenesService;

    @GetMapping
    public ResponseEntity<List<Imagenes>> getAllImagenes() {
        List<Imagenes> imagenes = imagenesService.findAll();
        if (imagenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(imagenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagenes> getImagenesById(@PathVariable Integer id) {
        Imagenes imagenes = imagenesService.findById(id);
        if (imagenes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imagenes);
    }

    @PostMapping
    public ResponseEntity<Imagenes> createImagenes(@RequestBody Imagenes imagenes) {
        Imagenes createdImagenes = imagenesService.save(imagenes);
        return ResponseEntity.status(201).body(createdImagenes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imagenes> updateImagenes(@PathVariable Integer id, @RequestBody Imagenes imagenes) {
        Imagenes existing = imagenesService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        imagenes.setIdImagen(id);
        Imagenes updated = imagenesService.save(imagenes);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Imagenes> patchImagenes(@PathVariable Integer id, @RequestBody Imagenes imagenes) {
        Imagenes patchedImagenes = imagenesService.patchImagenes(id, imagenes);
        if (patchedImagenes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedImagenes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagenes(@PathVariable Integer id) {
        if (imagenesService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        imagenesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
