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

import com.example.aska.model.Grado;
import com.example.aska.service.GradoService;

@RestController
@RequestMapping("/api/v1/grado")
public class GradoController {

    @Autowired
    private GradoService gradoService;

    @GetMapping
    public ResponseEntity<List<Grado>> getAllGrado() {
        List<Grado> grado = gradoService.findAll();
        if (grado.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(grado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grado> getGradoById(@PathVariable Integer id) {
        Grado grado = gradoService.findById(id);
        if (grado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grado);
    }

    @PostMapping
    public ResponseEntity<Grado> createGrado(@RequestBody Grado grado) {
        Grado createdGrado = gradoService.save(grado);
        return ResponseEntity.status(201).body(createdGrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grado> updateGrado(@PathVariable Integer id, @RequestBody Grado grado) {
        Grado existing = gradoService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        grado.setIdGrado(id);
        Grado updated = gradoService.save(grado);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Grado> patchGrado(@PathVariable Integer id, @RequestBody Grado grado) {
        Grado patchedGrado = gradoService.patchGrado(id, grado);
        if (patchedGrado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedGrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrado(@PathVariable Integer id) {
        if (gradoService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        gradoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
