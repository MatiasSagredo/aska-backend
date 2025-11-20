package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Grado;
import com.example.aska.model.Grados;
import com.example.aska.model.Producto;

@Repository
public interface GradosRepository extends JpaRepository<Grados, Integer> {

    void deleteByIdGrado(Grado grado);

    void deleteByIdProducto(Producto producto);

}
