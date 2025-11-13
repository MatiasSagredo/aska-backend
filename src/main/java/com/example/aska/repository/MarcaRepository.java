package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
