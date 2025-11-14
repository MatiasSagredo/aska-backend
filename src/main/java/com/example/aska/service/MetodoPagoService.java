package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.MetodoPago;
import com.example.aska.repository.MetodoPagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> findAll() {
        return metodoPagoRepository.findAll();
    }

    public MetodoPago findById(Integer id) {
        return metodoPagoRepository.findById(id).orElseThrow();
    }

    public MetodoPago save(MetodoPago diciplinas) {
        return metodoPagoRepository.save(diciplinas);
    }

    public MetodoPago patchMetodoPago(Integer id, MetodoPago metodoPago) {
        MetodoPago existingMetodoPago = metodoPagoRepository.findById(id).orElseThrow();

        if (metodoPago.getIdMetodoPago() != null) {
            existingMetodoPago.setMetodoPago(metodoPago.getMetodoPago());
        }

        return metodoPagoRepository.save(existingMetodoPago);
    }

    public void deleteById(Integer id) {
        metodoPagoRepository.deleteById(id);
    }
}
