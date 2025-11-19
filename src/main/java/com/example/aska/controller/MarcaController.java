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

import com.example.aska.model.Marca;
import com.example.aska.service.MarcaService;

@RestController
@RequestMapping("/api/v1/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> getAllMarca() {
        List<Marca> marca = marcaService.findAll();
        if (marca.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marca);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable Integer id) {
        Marca marca = marcaService.findById(id);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @PostMapping
    public ResponseEntity<Marca> createMarca(@RequestBody Marca marca) {
        Marca createdMarca = marcaService.save(marca);
        return ResponseEntity.status(201).body(createdMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        Marca existing = marcaService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        marca.setIdMarca(id);
        Marca updated = marcaService.save(marca);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Marca> patchMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        Marca patchedMarca = marcaService.patchMarca(id, marca);
        if (patchedMarca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedMarca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Integer id) {
        if (marcaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        marcaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
