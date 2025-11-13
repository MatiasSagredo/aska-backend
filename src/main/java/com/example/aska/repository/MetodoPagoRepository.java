package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.MetodoPago;
@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer>{}