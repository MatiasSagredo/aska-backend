package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.ProductosVenta;

@Repository
public interface ProductosVentaRepository extends JpaRepository<ProductosVenta, Integer> {
    
}
