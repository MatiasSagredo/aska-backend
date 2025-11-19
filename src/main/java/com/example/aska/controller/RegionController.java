package com.example.aska.controller;

import com.example.aska.model.Region;
import com.example.aska.service.RegionService;

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
@RequestMapping("/api/v1/regiones")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> getAllRoles() {
        List<Region> roles = regionService.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRolById(@PathVariable Integer id) {
        Region rol = regionService.findById(id);
        if(rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    @PostMapping
    public ResponseEntity<Region> createRol(@RequestBody Region rol) {
        Region newRol = regionService.save(rol);
        return ResponseEntity.status(201).body(newRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRol(@PathVariable Integer id, @RequestBody Region rol) {
        rol.setIdRegion(id);
        Region updatedRol = regionService.save(rol);
        if(updatedRol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRol);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Region> patchRol(@PathVariable Integer id, @RequestBody Region parcialRol) {
        Region updatedRol = regionService.patchRegion(id, parcialRol);
        if (updatedRol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Integer id) {
        if(regionService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        regionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}