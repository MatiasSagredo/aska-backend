package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Diciplina;
import com.example.aska.repository.DiciplinaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DiciplinaService {

    @Autowired
    private DiciplinaRepository diciplinaRepository;

    public List<Diciplina> findAll() {
        return diciplinaRepository.findAll();
    }

    public Diciplina findById(Integer id) {
        return diciplinaRepository.findById(id).orElseThrow();
    }

    public Diciplina save(Diciplina diciplina) {
        return diciplinaRepository.save(diciplina);
    }

    public void deleteById(Integer id) {
        diciplinaRepository.deleteById(id);
    }

    public Diciplina patchDiciplina(Integer id, Diciplina parcialDiciplina) {

        Diciplina listaToUpdate = findById(id);

        if (parcialDiciplina.getNombreDiciplina() != null) {
            listaToUpdate.setNombreDiciplina(parcialDiciplina.getNombreDiciplina());
        }

        return diciplinaRepository.save(listaToUpdate);
    }
}
