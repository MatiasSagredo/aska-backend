package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Estado;
import com.example.aska.repository.EstadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado findById(Integer id) {
        return estadoRepository.findById(id).orElseThrow();
    }

    public Estado save(Estado diciplinas) {
        return estadoRepository.save(diciplinas);
    }

    public Estado patchEstado(Integer id, Estado estado) {
        Estado existingEstado = estadoRepository.findById(id).orElseThrow();

        if (estado.getEstado() != null) {
            existingEstado.setEstado(estado.getEstado());
        }

        return estadoRepository.save(existingEstado);
    }

    public void deleteById(Integer id) {
        estadoRepository.deleteById(id);
    }
}
