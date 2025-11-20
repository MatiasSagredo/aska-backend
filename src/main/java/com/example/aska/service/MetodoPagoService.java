package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.aska.model.MetodoPago;
import com.example.aska.repository.MetodoPagoRepository;
import com.example.aska.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private VentaRepository ventaRepository;

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
        // Primero, verificar si el estudiante existe
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("metodoPago no encontrado"));

        //por que no hay un for acá, porque el id es único, no hay más de un estudiante con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya que no es necesario, este método se lo se ejecutará desde acá
        ventaRepository.deleteByIdMetodoPago(metodoPago);

        // Finalmente, eliminamos el estudiante
        metodoPagoRepository.delete(metodoPago);
    }
}
