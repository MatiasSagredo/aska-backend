package com.example.aska.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Colores;

@Repository
public interface ColoresRepository extends JpaRepository<Colores, Integer> {
}
