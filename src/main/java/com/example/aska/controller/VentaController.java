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

import com.example.aska.model.Venta;
import com.example.aska.service.VentaService;

@RestController
@RequestMapping("/api/v1/venta")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> getAllTalla() {
        List<Venta> venta = ventaService.findAll();
        if (venta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getTallaById(@PathVariable Integer id) {
        Venta venta = ventaService.findById(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    @PostMapping
    public ResponseEntity<Venta> createTalla(@RequestBody Venta venta) {
        Venta createdTalla = ventaService.save(venta);
        return ResponseEntity.status(201).body(createdTalla);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateTalla(@PathVariable Integer id, @RequestBody Venta venta) {
        Venta existing = ventaService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        venta.setIdVenta(id);
        Venta updated = ventaService.save(venta);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Venta> patchTalla(@PathVariable Integer id, @RequestBody Venta venta) {
        Venta patchedVenta = ventaService.patchTalla(id, venta);
        if (patchedVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTalla(@PathVariable Integer id) {
        if (ventaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        ventaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}