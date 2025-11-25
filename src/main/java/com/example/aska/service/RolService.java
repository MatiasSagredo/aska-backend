package com.example.aska.service;

import org.springframework.stereotype.Service;

import com.example.aska.model.Rol;
import com.example.aska.model.Usuario;
import com.example.aska.repository.RolRepository;
import com.example.aska.repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Rol findById(Integer id) {
        return rolRepository.findById(id).orElseThrow(null);
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public void deleteById(Integer id) {
        // Primero, verificar si el estudiante existe
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("rol no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        List<Usuario> usuarios = usuarioRepository.findByIdRol(rol);

        for (Usuario usuario : usuarios) {
            usuarioRepository.deleteById(usuario.getIdUsuario());
        }

        // Finalmente, eliminamos el estudiante
        rolRepository.delete(rol);
    }

    public Rol patchRol(Integer id, Rol parcialRol) {

        Rol listaToUpdate = findById(id);

        if (parcialRol.getNombreRol() != null) {
            listaToUpdate.setNombreRol(parcialRol.getNombreRol());
        }

        return rolRepository.save(listaToUpdate);
    }
}
