package com.example.aska.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Rol;
import com.example.aska.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByIdRol(Rol idRol);
    Usuario findByEmailUsuario(String email);

}
