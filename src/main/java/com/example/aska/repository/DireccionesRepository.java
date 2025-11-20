package com.example.aska.repository;

import com.example.aska.model.Comuna;
import com.example.aska.model.Direcciones;
import com.example.aska.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionesRepository extends JpaRepository<Direcciones, Integer> {

    void deleteByIdComuna(Comuna comuna);

    void deleteByIdUsuario(Usuario usuario);

}
