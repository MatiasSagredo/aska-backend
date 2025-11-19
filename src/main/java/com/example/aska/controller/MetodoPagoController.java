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

import com.example.aska.model.MetodoPago;
import com.example.aska.service.MetodoPagoService;

@RestController
@RequestMapping("/api/v1/metodopago")

public class MetodoPagoController {
    
    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public ResponseEntity<List<MetodoPago>> getAllUsuarios() {
        List<MetodoPago> usuarios = metodoPagoService.findAll();
        if(usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> getUsuarioById(@PathVariable Integer id) {
        MetodoPago usuario = metodoPagoService.findById(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<MetodoPago> createUsuario(@RequestBody MetodoPago usuario) {
        MetodoPago createdUsuario = metodoPagoService.save(usuario);
        return ResponseEntity.ok(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> updateUsuario(@PathVariable Integer id, @RequestBody MetodoPago usuario) {
        MetodoPago existing = metodoPagoService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setIdMetodoPago(id);
        MetodoPago updatedUsuario = metodoPagoService.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MetodoPago> partiallyUpdateUsuario(@PathVariable Integer id, @RequestBody MetodoPago usuario) {
        MetodoPago updatedUsuario = metodoPagoService.patchMetodoPago(id, usuario);
        if(updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        if(metodoPagoService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        metodoPagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
