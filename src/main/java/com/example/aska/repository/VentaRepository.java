package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Estado;
import com.example.aska.model.MetodoEnvio;
import com.example.aska.model.MetodoPago;
import com.example.aska.model.Usuario;
import com.example.aska.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    void deleteByIdUsuario(Usuario usuario);

    void deleteByIdMetodoPago(MetodoPago metodoPago);

    void deleteByIdMetodoEnvio(MetodoEnvio metodoEnvio);

    void deleteByIdEstado(Estado estado);

}
