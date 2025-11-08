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

import com.example.aska.model.Diciplinas;
import com.example.aska.service.DiciplinasService;

@RestController
@RequestMapping("/api/diciplinas")
public class DiciplinasController {

    @Autowired
    private DiciplinasService diciplinasService;

    @GetMapping
    public ResponseEntity<List<Diciplinas>> getAllDiciplinas() {
        List<Diciplinas> diciplinas = diciplinasService.findAll();
        if (diciplinas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(diciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diciplinas> getDiciplinasById(@PathVariable Integer id) {
        Diciplinas diciplinas = diciplinasService.findById(id);
        if (diciplinas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diciplinas);
    }

    @PostMapping
    public ResponseEntity<Diciplinas> createDiciplinas(@RequestBody Diciplinas diciplinas) {
        Diciplinas createdDiciplinas = diciplinasService.save(diciplinas);
        return ResponseEntity.status(201).body(createdDiciplinas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diciplinas> updateDiciplinas(@PathVariable Integer id,@RequestBody Diciplinas diciplinas) {
    Diciplinas existing = diciplinasService.findById(id);
    if (existing == null) {
        return ResponseEntity.notFound().build();
    }
        diciplinas.setIdDiciplinas(id);
        Diciplinas updated = diciplinasService.save(diciplinas);
        return ResponseEntity.ok(updated);
    }
/* 
    @PatchMapping("/{id}")
    public ResponseEntity<Diciplinas> patchDiciplinas(@PathVariable Integer id,@RequestBody Diciplinas diciplinas) {
        Diciplinas patchedDiciplinas = diciplinasService.patchDiciplinas(id, diciplinas);
        if (patchedDiciplinas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedDiciplinas);
    }
*/    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiciplinas(@PathVariable Integer id) {
        if (diciplinasService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        diciplinasService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }    
    
} 
