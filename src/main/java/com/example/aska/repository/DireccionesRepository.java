package com.example.aska.repository;

import com.example.aska.model.Direcciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionesRepository extends JpaRepository<Direcciones, Integer> {
}
