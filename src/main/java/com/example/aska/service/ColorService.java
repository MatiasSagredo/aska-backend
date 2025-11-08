package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Color;
import com.example.aska.repository.ColorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColorService {


    @Autowired
    private ColorRepository colorRepository;
    
    public List<Color> findAll(){
        return colorRepository.findAll();
    }

    public Color findById(Integer id){
        return colorRepository.findById(id).orElseThrow();
    }

    public Color save(Color color){
        return colorRepository.save(color);
    }

    public void deleteById(Integer id){
        colorRepository.deleteById(id);
    }


    public Color patchColor(Integer id, Color parcialColor){

        Color listaToUpdate =findById(id);
            
        if (parcialColor.getNombreColor() != null) {
                listaToUpdate.setNombreColor(parcialColor.getNombreColor());   
        }

        return colorRepository.save(listaToUpdate);
    } 
}