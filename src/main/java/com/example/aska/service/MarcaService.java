package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Marca;
import com.example.aska.repository.MarcaRepository;
import com.example.aska.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca findById(Integer id) {
        return marcaRepository.findById(id).orElseThrow();
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void deleteById(Integer id) {
        // Primero, verificar si el estudiante existe
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("marca no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        productoRepository.deleteByIdMarca(marca);

        // Finalmente, eliminamos el estudiante
        marcaRepository.delete(marca);
    }

    public Marca patchMarca(Integer id, Marca parcialMarca) {

        Marca listaToUpdate = findById(id);

        if (parcialMarca.getNombreMarca() != null) {
            listaToUpdate.setNombreMarca(parcialMarca.getNombreMarca());
        }

        return marcaRepository.save(listaToUpdate);
    }
}
