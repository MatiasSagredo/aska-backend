package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Talla;
import com.example.aska.model.Tallas;

@Repository
public interface TallasRepository extends JpaRepository<Tallas, Integer> {

    void deleteByIdTalla(Talla talla);

}
