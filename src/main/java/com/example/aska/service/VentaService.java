package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Venta;
import com.example.aska.repository.ProductosVentaRepository;
import com.example.aska.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

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
        // Primero, verificar si el estudiante existe
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        productosVentaRepository.deleteByIdVenta(venta);

        // Finalmente, eliminamos el estudiante
        ventaRepository.delete(venta);
    }

    public Venta patchTalla(Integer id, Venta parcialVenta) {

        Venta listaToUpdate = findById(id);

        if (parcialVenta.getTotal() != null) {
            listaToUpdate.setTotal(parcialVenta.getTotal());
        }

        return ventaRepository.save(listaToUpdate);
    }
}
