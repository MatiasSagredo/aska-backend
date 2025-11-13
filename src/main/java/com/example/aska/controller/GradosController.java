package com.example.aska.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aska.model.Grados;
import com.example.aska.service.GradosService;

@RestController
@RequestMapping("/api/grados")
public class GradosController {

    @Autowired
    private GradosService gradosService;

    @GetMapping
    public ResponseEntity<List<Grados>> getAllGrados() {
        List<Grados> grados = gradosService.findAll();
        if (grados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(grados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grados> getGradosById(@PathVariable Integer id) {
        Grados grados = gradosService.findById(id);
        if (grados == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grados);
    }

    @PostMapping
    public ResponseEntity<Grados> createGrados(@RequestBody Grados grados) {
        Grados createdGrados = gradosService.save(grados);
        return ResponseEntity.status(201).body(createdGrados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grados> updateGrados(@PathVariable Integer id, @RequestBody Grados grados) {
        Grados existing = gradosService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        grados.setIdGrados(id);
        Grados updated = gradosService.save(grados);
        return ResponseEntity.ok(updated);
    }

    /*
     * @PatchMapping("/{id}")
     * public ResponseEntity<Grados> patchGrados(@PathVariable Integer
     * id,@RequestBody Grados grados) {
     * Grados patchedGrados = gradosService.patchGrados(id, grados);
     * if (patchedGrados == null) {
     * return ResponseEntity.notFound().build();
     * }
     * return ResponseEntity.ok(patchedGrados);
     * }
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrados(@PathVariable Integer id) {
        if (gradosService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        gradosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
