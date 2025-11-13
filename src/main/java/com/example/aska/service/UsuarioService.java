package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Usuario;
import com.example.aska.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public Usuario save(Usuario usuario){
        String passwordHasheada = passwordEncoder.encode(usuario.getContrasenaUsuario());
        usuario.setContrasenaUsuario(passwordHasheada);
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario patchTalla(Integer id, Usuario parcialUsuario) {

        Usuario existingUsuario = usuarioRepository.findById(parcialUsuario.getIdUsuario()).orElse(null);

        if (existingUsuario != null) {
            if (parcialUsuario.getNombreUsuario() != null) {
                existingUsuario.setNombreUsuario(parcialUsuario.getNombreUsuario());
            }
            if (parcialUsuario.getEmailUsuario() != null) {
                existingUsuario.setEmailUsuario(parcialUsuario.getEmailUsuario());
            }

            if(parcialUsuario.getContrasenaUsuario() != null) {
                existingUsuario.setContrasenaUsuario(passwordEncoder.encode(parcialUsuario.getContrasenaUsuario()));
            }

            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }
}
