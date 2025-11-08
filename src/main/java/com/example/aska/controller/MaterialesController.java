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

import com.example.aska.model.Materiales;
import com.example.aska.service.MaterialesService;

@RestController
@RequestMapping("/api/materiales")
public class MaterialesController {

    
    @Autowired
    private MaterialesService materialesService;

    @GetMapping
    public ResponseEntity<List<Materiales>> getAllMateriales() {
        List<Materiales> materiales = materialesService.findAll();
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materiales> getMaterialesById(@PathVariable Integer id) {
        Materiales materiales = materialesService.findById(id);
        if (materiales == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @PostMapping
    public ResponseEntity<Materiales> createMateriales(@RequestBody Materiales materiales) {
        Materiales createdMateriales = materialesService.save(materiales);
        return ResponseEntity.status(201).body(createdMateriales);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materiales> updateMateriales(@PathVariable Integer id,@RequestBody Materiales materiales) {
    Materiales existing = materialesService.findById(id);
    if (existing == null) {
        return ResponseEntity.notFound().build();
    }
        materiales.setIdMateriales(id);
        Materiales updated = materialesService.save(materiales);
        return ResponseEntity.ok(updated);
    }
/* 
    @PatchMapping("/{id}")
    public ResponseEntity<Materiales> patchMateriales(@PathVariable Integer id,@RequestBody Materiales materiales) {
        Materiales patchedMateriales = materialesService.patchMateriales(id, materiales);
        if (patchedMateriales == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedMateriales);
    }
*/    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateriales(@PathVariable Integer id) {
        if (materialesService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        materialesService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }
}
