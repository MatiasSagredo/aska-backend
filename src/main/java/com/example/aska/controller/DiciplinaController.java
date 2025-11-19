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

import com.example.aska.model.Diciplina;
import com.example.aska.service.DiciplinaService;

@RestController
@RequestMapping("/api/v1/diciplina")
public class DiciplinaController {

    @Autowired
    private DiciplinaService diciplinaService;

    @GetMapping
    public ResponseEntity<List<Diciplina>> getAllDiciplina() {
        List<Diciplina> diciplina = diciplinaService.findAll();
        if (diciplina.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(diciplina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diciplina> getDiciplinaById(@PathVariable Integer id) {
        Diciplina diciplina = diciplinaService.findById(id);
        if (diciplina == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diciplina);
    }

    @PostMapping
    public ResponseEntity<Diciplina> createDiciplina(@RequestBody Diciplina diciplina) {
        Diciplina createdDiciplina = diciplinaService.save(diciplina);
        return ResponseEntity.status(201).body(createdDiciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diciplina> updateDiciplinas(@PathVariable Integer id, @RequestBody Diciplina diciplina) {
        Diciplina existing = diciplinaService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        diciplina.setIdDiciplina(id);
        Diciplina updated = diciplinaService.save(diciplina);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Diciplina> patchDiciplina(@PathVariable Integer id, @RequestBody Diciplina diciplina) {
        Diciplina patchedDiciplina = diciplinaService.patchDiciplina(id, diciplina);
        if (patchedDiciplina == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedDiciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiciplina(@PathVariable Integer id) {
        if (diciplinaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        diciplinaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
