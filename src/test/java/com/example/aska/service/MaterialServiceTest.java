package com.example.aska.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.aska.model.Material;
import com.example.aska.repository.MaterialRepository;
import com.example.aska.repository.MaterialesRepository;

@DisplayName("Pruebas unitarias para MaterialService")
class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private MaterialesRepository materialesRepository;

    @InjectMocks
    private MaterialService materialService;

    private Material materialTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        materialTest = new Material();
        materialTest.setIdMaterial(1);
        materialTest.setNombreMaterial("Algodón");
    }

    @Test
    @DisplayName("Debe obtener todos los materiales")
    void testFindAll() {
        // Arrange
        Material material2 = new Material();
        material2.setIdMaterial(2);
        material2.setNombreMaterial("Poliéster");
        
        List<Material> materiales = Arrays.asList(materialTest, material2);
        when(materialRepository.findAll()).thenReturn(materiales);

        // Act
        List<Material> resultado = materialService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Algodón", resultado.get(0).getNombreMaterial());
        verify(materialRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener un material por ID")
    void testFindById() {
        // Arrange
        when(materialRepository.findById(1)).thenReturn(Optional.of(materialTest));

        // Act
        Material resultado = materialService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdMaterial());
        assertEquals("Algodón", resultado.getNombreMaterial());
        verify(materialRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el material no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(materialRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> materialService.findById(999));
        verify(materialRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar un nuevo material")
    void testSave() {
        // Arrange
        when(materialRepository.save(materialTest)).thenReturn(materialTest);

        // Act
        Material resultado = materialService.save(materialTest);

        // Assert
        assertNotNull(resultado);
        assertEquals("Algodón", resultado.getNombreMaterial());
        verify(materialRepository, times(1)).save(materialTest);
    }

    @Test
    @DisplayName("Debe eliminar un material y sus dependencias")
    void testDeleteById() {
        // Arrange
        when(materialRepository.findById(1)).thenReturn(Optional.of(materialTest));
        doNothing().when(materialesRepository).deleteByIdMaterial(materialTest);
        doNothing().when(materialRepository).delete(materialTest);

        // Act
        materialService.deleteById(1);

        // Assert
        verify(materialRepository, times(1)).findById(1);
        verify(materialesRepository, times(1)).deleteByIdMaterial(materialTest);
        verify(materialRepository, times(1)).delete(materialTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar material inexistente")
    void testDeleteByIdNotFound() {
        // Arrange
        when(materialRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> materialService.deleteById(999));
        verify(materialRepository, times(1)).findById(999);
        verify(materialesRepository, never()).deleteByIdMaterial(any());
    }
}
