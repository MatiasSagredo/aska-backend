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

import com.example.aska.model.Direcciones;
import com.example.aska.service.DireccionesService;

@RestController
@RequestMapping("/api/v1/direcciones")

public class DireccionesController {
    
    @Autowired
    private DireccionesService direccionesService;

    @GetMapping
    public ResponseEntity<List<Direcciones>> getAllUsuarios() {
        List<Direcciones> usuarios = direccionesService.findAll();
        if(usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direcciones> getUsuarioById(@PathVariable Integer id) {
        Direcciones usuario = direccionesService.findById(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Direcciones> createUsuario(@RequestBody Direcciones usuario) {
        Direcciones createdUsuario = direccionesService.save(usuario);
        return ResponseEntity.ok(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direcciones> updateUsuario(@PathVariable Integer id, @RequestBody Direcciones usuario) {
        Direcciones existing = direccionesService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setIdDirecciones(id);
        Direcciones updatedUsuario = direccionesService.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Direcciones> partiallyUpdateUsuario(@PathVariable Integer id, @RequestBody Direcciones usuario) {
        Direcciones updatedUsuario = direccionesService.patchDirecciones(id, usuario);
        if(updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        if(direccionesService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        direccionesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
