package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Venta;
import com.example.aska.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta findById(Integer id) {
        return ventaRepository.findById(id).orElseThrow();
    }

    public Venta save(Venta marca) {
        return ventaRepository.save(marca);
    }

    public void deleteById(Integer id) {
        ventaRepository.deleteById(id);
    }

    public Venta patchTalla(Integer id, Venta parcialVenta) {

        Venta listaToUpdate = findById(id);

        if (parcialVenta.getTotal() != null) {
            listaToUpdate.setTotal(parcialVenta.getTotal());
        }

        return ventaRepository.save(listaToUpdate);
    }
}
