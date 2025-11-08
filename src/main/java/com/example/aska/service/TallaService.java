package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Talla;
import com.example.aska.repository.TallaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TallaService {
    

    @Autowired
    private TallaRepository tallaRepository;
    
    public List<Talla> findAll(){
        return tallaRepository.findAll();
    }

    public Talla findById(Integer id){
        return tallaRepository.findById(id).orElseThrow();
    }

    public Talla save(Talla marca){
        return tallaRepository.save(marca);
    }

    public void deleteById(Integer id){
        tallaRepository.deleteById(id);
    }

    public Talla patchTalla(Integer id, Talla parcialTalla){

        Talla listaToUpdate =findById(id);
            
        if (parcialTalla.getNombreTalla() != null) {
                listaToUpdate.setNombreTalla(parcialTalla.getNombreTalla());   
        }

        return tallaRepository.save(listaToUpdate);
    } 
}
