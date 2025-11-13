package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
