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

import com.example.aska.model.Rol;
import com.example.aska.model.Usuario;
import com.example.aska.repository.RolRepository;
import com.example.aska.repository.UsuarioRepository;

@DisplayName("Pruebas unitarias para RolService")
class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private RolService rolService;

    private Rol rolTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rolTest = new Rol();
        rolTest.setIdRol(1);
        rolTest.setNombreRol("ADMIN");
    }

    @Test
    @DisplayName("Debe obtener todos los roles")
    void testFindAll() {
        // Arrange
        Rol rol2 = new Rol();
        rol2.setIdRol(2);
        rol2.setNombreRol("USER");
        
        List<Rol> roles = Arrays.asList(rolTest, rol2);
        when(rolRepository.findAll()).thenReturn(roles);

        // Act
        List<Rol> resultado = rolService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("ADMIN", resultado.get(0).getNombreRol());
        verify(rolRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener un rol por ID")
    void testFindById() {
        // Arrange
        when(rolRepository.findById(1)).thenReturn(Optional.of(rolTest));

        // Act
        Rol resultado = rolService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdRol());
        assertEquals("ADMIN", resultado.getNombreRol());
        verify(rolRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el rol no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(rolRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> rolService.findById(999));
        verify(rolRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar un nuevo rol")
    void testSave() {
        // Arrange
        when(rolRepository.save(rolTest)).thenReturn(rolTest);

        // Act
        Rol resultado = rolService.save(rolTest);

        // Assert
        assertNotNull(resultado);
        assertEquals("ADMIN", resultado.getNombreRol());
        verify(rolRepository, times(1)).save(rolTest);
    }

    @Test
    @DisplayName("Debe actualizar un rol parcialmente")
    void testPatchRol() {
        // Arrange
        Rol rolActualizar = new Rol();
        rolActualizar.setNombreRol("MODERATOR");
        
        when(rolRepository.findById(1)).thenReturn(Optional.of(rolTest));
        when(rolRepository.save(any(Rol.class))).thenReturn(rolTest);

        // Act
        Rol resultado = rolService.patchRol(1, rolActualizar);

        // Assert
        assertNotNull(resultado);
        assertEquals("MODERATOR", resultado.getNombreRol());
        verify(rolRepository, times(1)).findById(1);
        verify(rolRepository, times(1)).save(any(Rol.class));
    }

    @Test
    @DisplayName("Debe eliminar un rol y todos sus usuarios asociados")
    void testDeleteById() {
        // Arrange
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1);
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2);
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(rolRepository.findById(1)).thenReturn(Optional.of(rolTest));
        when(usuarioRepository.findByIdRol(rolTest)).thenReturn(usuarios);
        doNothing().when(usuarioRepository).deleteById(1);
        doNothing().when(usuarioRepository).deleteById(2);
        doNothing().when(rolRepository).delete(rolTest);

        // Act
        rolService.deleteById(1);

        // Assert
        verify(rolRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).findByIdRol(rolTest);
        verify(usuarioRepository, times(1)).deleteById(1);
        verify(usuarioRepository, times(1)).deleteById(2);
        verify(rolRepository, times(1)).delete(rolTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar rol inexistente")
    void testDeleteByIdNotFound() {
        // Arrange
        when(rolRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> rolService.deleteById(999));
        verify(rolRepository, times(1)).findById(999);
        verify(usuarioRepository, never()).findByIdRol(any());
    }

    @Test
    @DisplayName("Debe eliminar rol sin usuarios asociados")
    void testDeleteByIdWithoutUsers() {
        // Arrange
        when(rolRepository.findById(1)).thenReturn(Optional.of(rolTest));
        when(usuarioRepository.findByIdRol(rolTest)).thenReturn(Arrays.asList());
        doNothing().when(rolRepository).delete(rolTest);

        // Act
        rolService.deleteById(1);

        // Assert
        verify(rolRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).findByIdRol(rolTest);
        verify(usuarioRepository, never()).deleteById(any());
        verify(rolRepository, times(1)).delete(rolTest);
    }
}
