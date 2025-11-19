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

import com.example.aska.model.Estado;
import com.example.aska.service.EstadoService;

@RestController
@RequestMapping("/api/v1/estados")

public class EstadoController {
    
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> getAllUsuarios() {
        List<Estado> usuarios = estadoService.findAll();
        if(usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> getUsuarioById(@PathVariable Integer id) {
        Estado usuario = estadoService.findById(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Estado> createUsuario(@RequestBody Estado usuario) {
        Estado createdUsuario = estadoService.save(usuario);
        return ResponseEntity.ok(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> updateUsuario(@PathVariable Integer id, @RequestBody Estado usuario) {
        Estado existing = estadoService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setIdEstado(id);
        Estado updatedUsuario = estadoService.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Estado> partiallyUpdateUsuario(@PathVariable Integer id, @RequestBody Estado usuario) {
        Estado updatedUsuario = estadoService.patchEstado(id, usuario);
        if(updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        if(estadoService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        estadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
