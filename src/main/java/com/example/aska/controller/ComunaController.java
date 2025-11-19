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

import com.example.aska.model.Comuna;
import com.example.aska.service.ComunaService;

@RestController
@RequestMapping("/api/v1/comunas")

public class ComunaController {
    
    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<Comuna>> getAllUsuarios() {
        List<Comuna> usuarios = comunaService.findAll();
        if(usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> getUsuarioById(@PathVariable Integer id) {
        Comuna usuario = comunaService.findById(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Comuna> createUsuario(@RequestBody Comuna usuario) {
        Comuna createdUsuario = comunaService.save(usuario);
        return ResponseEntity.ok(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> updateUsuario(@PathVariable Integer id, @RequestBody Comuna usuario) {
        Comuna existing = comunaService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setIdComuna(id);
        Comuna updatedUsuario = comunaService.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comuna> partiallyUpdateUsuario(@PathVariable Integer id, @RequestBody Comuna usuario) {
        Comuna updatedUsuario = comunaService.patchComuna(id, usuario);
        if(updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        if(comunaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        comunaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
