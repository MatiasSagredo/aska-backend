package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.ProductosVenta;
import com.example.aska.repository.ProductosVentaRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ProductosVentaService {
    
    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public List<ProductosVenta> findAll() {
        return productosVentaRepository.findAll();
    }

    public ProductosVenta findById(Integer id) {
        return productosVentaRepository.findById(id).orElseThrow();
    }

    public ProductosVenta save(ProductosVenta marca) {
        return productosVentaRepository.save(marca);
    }

    public void deleteById(Integer id) {
        productosVentaRepository.deleteById(id);
    }

}
