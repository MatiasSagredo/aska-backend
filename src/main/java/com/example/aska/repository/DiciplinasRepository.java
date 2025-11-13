package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Diciplinas;

@Repository
public interface DiciplinasRepository extends JpaRepository<Diciplinas, Integer> {

}
