package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {



}
