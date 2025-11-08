package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Producto;
import com.example.aska.repository.ProductoRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ProductoService {

    
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Producto findById(Integer id){
        return productoRepository.findById(id).orElseThrow();
    }

    public Producto save(Producto marca){
        return productoRepository.save(marca);
    }

    public void deleteById(Integer id){
        productoRepository.deleteById(id);
    }

    public Producto patchProducto(Integer id, Producto parcialProducto){

        Producto listaToUpdate =findById(id);
            
        if (parcialProducto.getNombreProducto() != null) {
                listaToUpdate.setNombreProducto(parcialProducto.getNombreProducto());   
        }
        if (parcialProducto.getDescripcion() != null) {
                listaToUpdate.setDescripcion(parcialProducto.getDescripcion());   
        }

        return productoRepository.save(listaToUpdate);
    } 
}
