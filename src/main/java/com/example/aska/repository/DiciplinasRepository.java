package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Diciplina;
import com.example.aska.model.Diciplinas;
import com.example.aska.model.Producto;

@Repository
public interface DiciplinasRepository extends JpaRepository<Diciplinas, Integer> {

    void deleteByIdDiciplina(Diciplina diciplina);

    void deleteByIdProducto(Producto producto);

}
