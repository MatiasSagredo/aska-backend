package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Venta;
@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {}