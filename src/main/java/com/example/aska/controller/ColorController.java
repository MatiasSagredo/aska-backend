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

import com.example.aska.model.Color;
import com.example.aska.service.ColorService;


@RestController
@RequestMapping("/api/v1/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<List<Color>> getAllColor() {
        List<Color> color = colorService.findAll();
        if (color.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(color);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> getColorById(@PathVariable Integer id) {
        Color color = colorService.findById(id);
        if (color == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(color);
    }

    @PostMapping
    public ResponseEntity<Color> createColor(@RequestBody Color color) {
        Color createdColor = colorService.save(color);
        return ResponseEntity.status(201).body(createdColor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Color> updateColor(@PathVariable Integer id,@RequestBody Color color) {
    Color existing = colorService.findById(id);
    if (existing == null) {
        return ResponseEntity.notFound().build();
    }
        color.setIdColor(id);
        Color updated = colorService.save(color);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Color> patchColor(@PathVariable Integer id,@RequestBody Color color) {
        Color patchedColor = colorService.patchColor(id, color);
        if (patchedColor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedColor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Integer id) {
        if (colorService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        colorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
