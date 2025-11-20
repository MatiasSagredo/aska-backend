package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Color;
import com.example.aska.model.Colores;
import com.example.aska.model.Producto;

@Repository
public interface ColoresRepository extends JpaRepository<Colores, Integer> {

    void deleteByIdColor(Color color);

    void deleteByIdProducto(Producto producto);

}
