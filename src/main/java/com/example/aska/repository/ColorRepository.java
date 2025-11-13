package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

}
