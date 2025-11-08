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

import com.example.aska.model.Tallas;
import com.example.aska.service.TallasService;


@RestController
@RequestMapping("/api/tallas")
public class TallasController {
    
        @Autowired
    private TallasService tallasService;

    @GetMapping
    public ResponseEntity<List<Tallas>> getAllTallas() {
        List<Tallas> tallas = tallasService.findAll();
        if (tallas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tallas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tallas> getTallasById(@PathVariable Integer id) {
        Tallas tallas = tallasService.findById(id);
        if (tallas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tallas);
    }

    @PostMapping
    public ResponseEntity<Tallas> createTallas(@RequestBody Tallas tallas) {
        Tallas createdTallas = tallasService.save(tallas);
        return ResponseEntity.status(201).body(createdTallas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tallas> updateTallas(@PathVariable Integer id,@RequestBody Tallas tallas) {
    Tallas existing = tallasService.findById(id);
    if (existing == null) {
        return ResponseEntity.notFound().build();
    }
        tallas.setIdTallas(id);
        Tallas updated = tallasService.save(tallas);
        return ResponseEntity.ok(updated);
    }

/* 
    @PatchMapping("/{id}")
    public ResponseEntity<Tallas> patchTallas(@PathVariable Integer id,@RequestBody Tallas tallas) {
        Tallas patchedTallas = tallasService.patchTallas(id, tallas);
        if (patchedTallas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedTallas);
    }
*/    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTallas(@PathVariable Integer id) {
        if (tallasService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        tallasService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }
}
