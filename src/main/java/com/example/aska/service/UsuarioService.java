package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Usuario;
import com.example.aska.repository.DireccionesRepository;
import com.example.aska.repository.UsuarioRepository;
import com.example.aska.repository.VentaRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DireccionesRepository direccionesRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public Usuario save(Usuario usuario) {
        String passwordHasheada = passwordEncoder.encode(usuario.getContrasenaUsuario());
        usuario.setContrasenaUsuario(passwordHasheada);
        return usuarioRepository.save(usuario);
    }

    public Usuario login(Usuario usuario) {
        if (usuario == null || usuario.getEmailUsuario() == null) {
            return null;
        }

        Usuario usuarioLogin = usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario());
        if (usuarioLogin == null) {
            return null;
        }

        if (passwordEncoder.matches(usuario.getContrasenaUsuario(), usuarioLogin.getContrasenaUsuario())) {
            return usuarioLogin;
        }
        return null;
    }

    public void deleteById(Integer id) {
        // Primero, verificar si el estudiante existe
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

        // por que no hay un for acá, porque el id es único, no hay más de un estudiante
        // con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya
        // que no es necesario, este método se lo se ejecutará desde acá
        direccionesRepository.deleteByIdUsuario(usuario);
        ventaRepository.deleteByIdUsuario(usuario);

        // Finalmente, eliminamos el estudiante
        usuarioRepository.delete(usuario);
    }

    public Usuario patchUsuario(Integer id, Usuario parcialUsuario) {

        Usuario existingUsuario = findById(id);

        if (existingUsuario != null) {
            if (parcialUsuario.getNombreUsuario() != null) {
                existingUsuario.setNombreUsuario(parcialUsuario.getNombreUsuario());
            }
            if (parcialUsuario.getEmailUsuario() != null) {
                existingUsuario.setEmailUsuario(parcialUsuario.getEmailUsuario());
            }

            if (parcialUsuario.getContrasenaUsuario() != null) {
                existingUsuario.setContrasenaUsuario(passwordEncoder.encode(parcialUsuario.getContrasenaUsuario()));
            }

            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }
}
