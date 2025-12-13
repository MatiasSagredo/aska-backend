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

import com.example.aska.model.Marca;
import com.example.aska.repository.MarcaRepository;
import com.example.aska.repository.ProductoRepository;

@DisplayName("Pruebas unitarias para MarcaService")
class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private MarcaService marcaService;

    private Marca marcaTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        marcaTest = new Marca();
        marcaTest.setIdMarca(1);
        marcaTest.setNombreMarca("Nike");
    }

    @Test
    @DisplayName("Debe obtener todas las marcas")
    void testFindAll() {
        // Arrange
        Marca marca2 = new Marca();
        marca2.setIdMarca(2);
        marca2.setNombreMarca("Adidas");
        
        List<Marca> marcas = Arrays.asList(marcaTest, marca2);
        when(marcaRepository.findAll()).thenReturn(marcas);

        // Act
        List<Marca> resultado = marcaService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Nike", resultado.get(0).getNombreMarca());
        verify(marcaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener una marca por ID")
    void testFindById() {
        // Arrange
        when(marcaRepository.findById(1)).thenReturn(Optional.of(marcaTest));

        // Act
        Marca resultado = marcaService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdMarca());
        assertEquals("Nike", resultado.getNombreMarca());
        verify(marcaRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la marca no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(marcaRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> marcaService.findById(999));
        verify(marcaRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar una nueva marca")
    void testSave() {
        // Arrange
        when(marcaRepository.save(marcaTest)).thenReturn(marcaTest);

        // Act
        Marca resultado = marcaService.save(marcaTest);

        // Assert
        assertNotNull(resultado);
        assertEquals("Nike", resultado.getNombreMarca());
        verify(marcaRepository, times(1)).save(marcaTest);
    }

    @Test
    @DisplayName("Debe actualizar una marca parcialmente")
    void testPatchMarca() {
        // Arrange
        Marca marcaActualizar = new Marca();
        marcaActualizar.setNombreMarca("Nike Plus");
        
        when(marcaRepository.findById(1)).thenReturn(Optional.of(marcaTest));
        when(marcaRepository.save(any(Marca.class))).thenReturn(marcaTest);

        // Act
        Marca resultado = marcaService.patchMarca(1, marcaActualizar);

        // Assert
        assertNotNull(resultado);
        assertEquals("Nike Plus", resultado.getNombreMarca());
        verify(marcaRepository, times(1)).findById(1);
        verify(marcaRepository, times(1)).save(any(Marca.class));
    }

    @Test
    @DisplayName("Debe eliminar una marca y sus dependencias")
    void testDeleteById() {
        // Arrange
        when(marcaRepository.findById(1)).thenReturn(Optional.of(marcaTest));
        doNothing().when(productoRepository).deleteByIdMarca(marcaTest);
        doNothing().when(marcaRepository).delete(marcaTest);

        // Act
        marcaService.deleteById(1);

        // Assert
        verify(marcaRepository, times(1)).findById(1);
        verify(productoRepository, times(1)).deleteByIdMarca(marcaTest);
        verify(marcaRepository, times(1)).delete(marcaTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar marca inexistente")
    void testDeleteByIdNotFound() {
        // Arrange
        when(marcaRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> marcaService.deleteById(999));
        verify(marcaRepository, times(1)).findById(999);
        verify(productoRepository, never()).deleteByIdMarca(any());
    }
}
