package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.MetodoEnvio;
@Repository
public interface MetodoEnvioRepository extends JpaRepository<MetodoEnvio, Integer>{}