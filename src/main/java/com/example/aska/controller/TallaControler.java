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

import com.example.aska.model.Talla;
import com.example.aska.service.TallaService;

@RestController
@RequestMapping("/api/v1/talla")
public class TallaControler {

    @Autowired
    private TallaService tallaService;

    @GetMapping
    public ResponseEntity<List<Talla>> getAllTalla() {
        List<Talla> talla = tallaService.findAll();
        if (talla.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(talla);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Talla> getTallaById(@PathVariable Integer id) {
        Talla talla = tallaService.findById(id);
        if (talla == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(talla);
    }

    @PostMapping
    public ResponseEntity<Talla> createTalla(@RequestBody Talla talla) {
        Talla createdTalla = tallaService.save(talla);
        return ResponseEntity.status(201).body(createdTalla);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Talla> updateTalla(@PathVariable Integer id, @RequestBody Talla talla) {
        Talla existing = tallaService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        talla.setIdTalla(id);
        Talla updated = tallaService.save(talla);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Talla> patchTalla(@PathVariable Integer id, @RequestBody Talla talla) {
        Talla patchedTalla = tallaService.patchTalla(id, talla);
        if (patchedTalla == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedTalla);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTalla(@PathVariable Integer id) {
        if (tallaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        tallaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
