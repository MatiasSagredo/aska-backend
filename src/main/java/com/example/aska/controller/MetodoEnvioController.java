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

import com.example.aska.model.MetodoEnvio;
import com.example.aska.service.MetodoEnvioService;

@RestController
@RequestMapping("/api/v1/metodoenvio")

public class MetodoEnvioController {
    
    @Autowired
    private MetodoEnvioService metodoEnvioService;

    @GetMapping
    public ResponseEntity<List<MetodoEnvio>> getAllUsuarios() {
        List<MetodoEnvio> usuarios = metodoEnvioService.findAll();
        if(usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoEnvio> getUsuarioById(@PathVariable Integer id) {
        MetodoEnvio usuario = metodoEnvioService.findById(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<MetodoEnvio> createUsuario(@RequestBody MetodoEnvio usuario) {
        MetodoEnvio createdUsuario = metodoEnvioService.save(usuario);
        return ResponseEntity.ok(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoEnvio> updateUsuario(@PathVariable Integer id, @RequestBody MetodoEnvio usuario) {
        MetodoEnvio existing = metodoEnvioService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setIdMetodoEnvio(id);
        MetodoEnvio updatedUsuario = metodoEnvioService.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MetodoEnvio> partiallyUpdateUsuario(@PathVariable Integer id, @RequestBody MetodoEnvio usuario) {
        MetodoEnvio updatedUsuario = metodoEnvioService.patchMetodoEnvio(id, usuario);
        if(updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        if(metodoEnvioService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        metodoEnvioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
