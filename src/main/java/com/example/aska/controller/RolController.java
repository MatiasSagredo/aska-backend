package com.example.aska.controller;

import com.example.aska.model.Rol;
import com.example.aska.service.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolService.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Integer id) {
        Rol rol = rolService.findById(id);
        if(rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol newRol = rolService.save(rol);
        return ResponseEntity.status(201).body(newRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable Integer id, @RequestBody Rol rol) {
        rol.setIdRol(id);
        Rol updatedRol = rolService.save(rol);
        if(updatedRol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRol);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Rol> patchRol(@PathVariable Integer id, @RequestBody Rol parcialRol) {
        Rol updatedRol = rolService.patchRol(id, parcialRol);
        if (updatedRol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Integer id) {
        if(rolService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}