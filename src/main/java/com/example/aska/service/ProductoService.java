package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Producto;
import com.example.aska.repository.ColoresRepository;
import com.example.aska.repository.DiciplinasRepository;
import com.example.aska.repository.GradosRepository;
import com.example.aska.repository.ImagenesRepository;
import com.example.aska.repository.MaterialesRepository;
import com.example.aska.repository.ProductoRepository;
import com.example.aska.repository.ProductosVentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ColoresRepository coloresRepository;

    @Autowired
    private MaterialesRepository materialesRepository;

    @Autowired
    private DiciplinasRepository diciplinasRepository;

    @Autowired
    private GradosRepository gradosRepository;



    @Autowired
    private ImagenesRepository imagenesRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Integer id) {
        return productoRepository.findById(id).orElseThrow();
    }

    public Producto save(Producto marca) {
        return productoRepository.save(marca);
    }

    public void deleteById(Integer id) {
        // Primero, verificar si el estudiante existe
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("producto no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        coloresRepository.deleteByIdProducto(producto);
        materialesRepository.deleteByIdProducto(producto);
        diciplinasRepository.deleteByIdProducto(producto);
        gradosRepository.deleteByIdProducto(producto);
        imagenesRepository.deleteByIdProducto(producto);
        productosVentaRepository.deleteByIdProducto(producto);

        // Finalmente, eliminamos el estudiante
        productoRepository.delete(producto);
    }

    public Producto patchProducto(Integer id, Producto parcialProducto) {

        Producto listaToUpdate = findById(id);

        if (parcialProducto.getNombreProducto() != null) {
            listaToUpdate.setNombreProducto(parcialProducto.getNombreProducto());
        }
        if (parcialProducto.getDescripcion() != null) {
            listaToUpdate.setDescripcion(parcialProducto.getDescripcion());
        }

        return productoRepository.save(listaToUpdate);
    }
}
