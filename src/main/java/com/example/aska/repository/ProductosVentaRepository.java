package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Producto;
import com.example.aska.model.ProductosVenta;
import com.example.aska.model.Venta;

@Repository
public interface ProductosVentaRepository extends JpaRepository<ProductosVenta, Integer> {

    void deleteByIdProducto(Producto producto);

    void deleteByIdVenta(Venta venta);

}
