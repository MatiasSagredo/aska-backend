package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Estado;
import com.example.aska.repository.EstadoRepository;
import com.example.aska.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private VentaRepository ventaRepository;

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
        // Primero, verificar si el estudiante existe
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        ventaRepository.deleteByIdEstado(estado);

        // Finalmente, eliminamos el estudiante
        estadoRepository.delete(estado);
    }

}
