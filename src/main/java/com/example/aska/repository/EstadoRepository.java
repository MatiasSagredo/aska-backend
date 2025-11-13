package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Estado;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{}