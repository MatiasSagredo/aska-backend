package com.example.aska.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.aska.model.Usuario;
import com.example.aska.repository.DireccionesRepository;
import com.example.aska.repository.UsuarioRepository;
import com.example.aska.repository.VentaRepository;

@DisplayName("Pruebas unitarias para UsuarioService")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private DireccionesRepository direccionesRepository;

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioTest = new Usuario();
        usuarioTest.setIdUsuario(1);
        usuarioTest.setNombreUsuario("Juan Pérez");
        usuarioTest.setEmailUsuario("juan@example.com");
        usuarioTest.setContrasenaUsuario("contraseña123");
    }

    @Test
    @DisplayName("Debe obtener todos los usuarios")
    void testFindAll() {
        // Arrange
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2);
        usuario2.setNombreUsuario("María García");
        
        List<Usuario> usuarios = Arrays.asList(usuarioTest, usuario2);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> resultado = usuarioService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombreUsuario());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener un usuario por ID")
    void testFindById() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioTest));

        // Act
        Usuario resultado = usuarioService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdUsuario());
        assertEquals("Juan Pérez", resultado.getNombreUsuario());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el usuario no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(usuarioRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> usuarioService.findById(999));
        verify(usuarioRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar un nuevo usuario con contraseña hasheada")
    void testSave() {
        // Arrange
        String passwordHash = "$2a$10$hashedPassword";
        when(passwordEncoder.encode("contraseña123")).thenReturn(passwordHash);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioTest);

        // Act
        Usuario resultado = usuarioService.save(usuarioTest);

        // Assert
        assertNotNull(resultado);
        assertEquals("Juan Pérez", resultado.getNombreUsuario());
        verify(passwordEncoder, times(1)).encode("contraseña123");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Debe realizar login exitoso")
    void testLoginSuccess() {
        // Arrange
        Usuario usuarioLogin = new Usuario();
        usuarioLogin.setEmailUsuario("juan@example.com");
        usuarioLogin.setContrasenaUsuario("contraseña123");

        usuarioTest.setContrasenaUsuario("$2a$10$hashedPassword");
        
        when(usuarioRepository.findByEmailUsuario("juan@example.com")).thenReturn(usuarioTest);
        when(passwordEncoder.matches("contraseña123", usuarioTest.getContrasenaUsuario())).thenReturn(true);

        // Act
        Usuario resultado = usuarioService.login(usuarioLogin);

        // Assert
        assertNotNull(resultado);
        assertEquals("juan@example.com", resultado.getEmailUsuario());
        verify(usuarioRepository, times(1)).findByEmailUsuario("juan@example.com");
    }

    @Test
    @DisplayName("Debe fallar login con contraseña incorrecta")
    void testLoginFailedWrongPassword() {
        // Arrange
        Usuario usuarioLogin = new Usuario();
        usuarioLogin.setEmailUsuario("juan@example.com");
        usuarioLogin.setContrasenaUsuario("contraseñaIncorrecta");

        usuarioTest.setContrasenaUsuario("$2a$10$hashedPassword");
        
        when(usuarioRepository.findByEmailUsuario("juan@example.com")).thenReturn(usuarioTest);
        when(passwordEncoder.matches("contraseñaIncorrecta", usuarioTest.getContrasenaUsuario())).thenReturn(false);

        // Act
        Usuario resultado = usuarioService.login(usuarioLogin);

        // Assert
        assertNull(resultado);
        verify(usuarioRepository, times(1)).findByEmailUsuario("juan@example.com");
    }

    @Test
    @DisplayName("Debe fallar login con usuario no encontrado")
    void testLoginFailedUserNotFound() {
        // Arrange
        Usuario usuarioLogin = new Usuario();
        usuarioLogin.setEmailUsuario("noexiste@example.com");
        usuarioLogin.setContrasenaUsuario("contraseña123");

        when(usuarioRepository.findByEmailUsuario("noexiste@example.com")).thenReturn(null);

        // Act
        Usuario resultado = usuarioService.login(usuarioLogin);

        // Assert
        assertNull(resultado);
        verify(usuarioRepository, times(1)).findByEmailUsuario("noexiste@example.com");
    }

    @Test
    @DisplayName("Debe fallar login con usuario null")
    void testLoginFailedNullUsuario() {
        // Act
        Usuario resultado = usuarioService.login(null);

        // Assert
        assertNull(resultado);
        verify(usuarioRepository, never()).findByEmailUsuario(any());
    }

    @Test
    @DisplayName("Debe eliminar un usuario y sus dependencias")
    void testDeleteById() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioTest));
        doNothing().when(direccionesRepository).deleteByIdUsuario(usuarioTest);
        doNothing().when(ventaRepository).deleteByIdUsuario(usuarioTest);
        doNothing().when(usuarioRepository).delete(usuarioTest);

        // Act
        usuarioService.deleteById(1);

        // Assert
        verify(usuarioRepository, times(1)).findById(1);
        verify(direccionesRepository, times(1)).deleteByIdUsuario(usuarioTest);
        verify(ventaRepository, times(1)).deleteByIdUsuario(usuarioTest);
        verify(usuarioRepository, times(1)).delete(usuarioTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar usuario inexistente")
    void testDeleteByIdNotFound() {
        // Arrange
        when(usuarioRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> usuarioService.deleteById(999));
        verify(usuarioRepository, times(1)).findById(999);
        verify(direccionesRepository, never()).deleteByIdUsuario(any());
    }
}
