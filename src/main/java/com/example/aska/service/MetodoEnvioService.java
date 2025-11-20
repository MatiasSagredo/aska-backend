package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.MetodoEnvio;
import com.example.aska.repository.MetodoEnvioRepository;
import com.example.aska.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoEnvioService {

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    @Autowired
    private VentaRepository ventaRepository;

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
        // Primero, verificar si el estudiante existe
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MetodoEnvio no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        ventaRepository.deleteByIdMetodoEnvio(metodoEnvio);

        // Finalmente, eliminamos el estudiante
        metodoEnvioRepository.delete(metodoEnvio);
    }
}
