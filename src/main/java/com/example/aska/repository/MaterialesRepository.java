package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Material;
import com.example.aska.model.Materiales;
import com.example.aska.model.Producto;

@Repository
public interface MaterialesRepository extends JpaRepository<Materiales, Integer> {

    void deleteByIdMaterial(Material material);

    void deleteByIdProducto(Producto producto);
}
