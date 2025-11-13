package com.example.aska.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

// import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aska.model.Colores;
import com.example.aska.service.ColoresService;

@RestController
@RequestMapping("/api/colores")
public class ColoresController {

    @Autowired
    private ColoresService coloresService;

    @GetMapping
    public ResponseEntity<List<Colores>> getAllColores() {
        List<Colores> colores = coloresService.findAll();
        if (colores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colores> getColoresById(@PathVariable Integer id) {
        Colores colores = coloresService.findById(id);
        if (colores == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(colores);
    }

    @PostMapping
    public ResponseEntity<Colores> createColores(@RequestBody Colores colores) {
        Colores createdColores = coloresService.save(colores);
        return ResponseEntity.status(201).body(createdColores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colores> updateColores(@PathVariable Integer id, @RequestBody Colores colores) {
        Colores existing = coloresService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        colores.setIdColores(id);
        Colores updated = coloresService.save(colores);
        return ResponseEntity.ok(updated);
    }

    /*
     * @PatchMapping("/{id}")
     * public ResponseEntity<Colores> patchColores(@PathVariable Integer
     * id,@RequestBody Colores colores) {
     * Colores patchedColores = coloresService.patchColores(id, colores);
     * if (patchedColores == null) {
     * return ResponseEntity.notFound().build();
     * }
     * return ResponseEntity.ok(patchedColores);
     * }
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColores(@PathVariable Integer id) {
        if (coloresService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        coloresService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
