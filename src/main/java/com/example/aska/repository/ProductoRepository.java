package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Marca;
import com.example.aska.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    void deleteByIdMarca(Marca marca);

}
