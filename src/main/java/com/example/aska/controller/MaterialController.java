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

import com.example.aska.model.Material;
import com.example.aska.service.MaterialService;

@RestController
@RequestMapping("/api/v1/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterial() {
        List<Material> material = materialService.findAll();
        if (material.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(material);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Integer id) {
        Material material = materialService.findById(id);
        if (material == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(material);
    }

    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        Material createdMaterial = materialService.save(material);
        return ResponseEntity.status(201).body(createdMaterial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable Integer id, @RequestBody Material material) {
        Material existing = materialService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        material.setIdMaterial(id);
        Material updated = materialService.save(material);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Material> patchMaterial(@PathVariable Integer id, @RequestBody Material material) {
        Material patchedMaterial = materialService.patchMaterial(id, material);
        if (patchedMaterial == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedMaterial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Integer id) {
        if (materialService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        materialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
