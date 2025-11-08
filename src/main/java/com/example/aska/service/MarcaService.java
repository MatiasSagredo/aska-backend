package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Marca;
import com.example.aska.repository.MarcaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;
    
    public List<Marca> findAll(){
        return marcaRepository.findAll();
    }

    public Marca findById(Integer id){
        return marcaRepository.findById(id).orElseThrow();
    }

    public Marca save(Marca marca){
        return marcaRepository.save(marca);
    }

    public void deleteById(Integer id){
        marcaRepository.deleteById(id);
    }


    public Marca patchMarca(Integer id, Marca parcialMarca){

        Marca listaToUpdate =findById(id);
            
        if (parcialMarca.getNombreMarca() != null) {
                listaToUpdate.setNombreMarca(parcialMarca.getNombreMarca());   
        }

        return marcaRepository.save(listaToUpdate);
    } 
}
