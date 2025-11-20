package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Imagenes;
import com.example.aska.model.Producto;

@Repository
public interface ImagenesRepository extends JpaRepository<Imagenes, Integer> {

    void deleteByIdProducto(Producto producto);

}
