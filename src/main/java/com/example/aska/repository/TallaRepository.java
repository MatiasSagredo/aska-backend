package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Talla;

@Repository
public interface TallaRepository extends JpaRepository<Talla, Integer> {

}
