package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.MetodoEnvio;
import com.example.aska.repository.MetodoEnvioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoEnvioService {

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    public List<MetodoEnvio> findAll() {
        return metodoEnvioRepository.findAll();
    }

    public MetodoEnvio findById(Integer id) {
        return metodoEnvioRepository.findById(id).orElseThrow();
    }

    public MetodoEnvio save(MetodoEnvio diciplinas) {
        return metodoEnvioRepository.save(diciplinas);
    }

    public MetodoEnvio patchMetodoEnvio(Integer id, MetodoEnvio metodoEnvio) {
        MetodoEnvio existingMetodoEnvio = metodoEnvioRepository.findById(id).orElseThrow();

        if (metodoEnvio.getMetodoEnvio() != null) {
            existingMetodoEnvio.setMetodoEnvio(metodoEnvio.getMetodoEnvio());
        }

        return metodoEnvioRepository.save(existingMetodoEnvio);
    }

    public void deleteById(Integer id) {
        metodoEnvioRepository.deleteById(id);
    }
}
