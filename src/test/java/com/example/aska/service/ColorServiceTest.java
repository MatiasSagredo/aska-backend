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

import com.example.aska.model.Color;
import com.example.aska.repository.ColorRepository;
import com.example.aska.repository.ColoresRepository;

@DisplayName("Pruebas unitarias para ColorService")
class ColorServiceTest {

    @Mock
    private ColorRepository colorRepository;

    @Mock
    private ColoresRepository coloresRepository;

    @InjectMocks
    private ColorService colorService;

    private Color colorTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        colorTest = new Color();
        colorTest.setIdColor(1);
        colorTest.setNombreColor("Rojo");
    }

    @Test
    @DisplayName("Debe obtener todos los colores")
    void testFindAll() {
        // Arrange
        Color color2 = new Color();
        color2.setIdColor(2);
        color2.setNombreColor("Azul");
        
        List<Color> colores = Arrays.asList(colorTest, color2);
        when(colorRepository.findAll()).thenReturn(colores);

        // Act
        List<Color> resultado = colorService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Rojo", resultado.get(0).getNombreColor());
        verify(colorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener un color por ID")
    void testFindById() {
        // Arrange
        when(colorRepository.findById(1)).thenReturn(Optional.of(colorTest));

        // Act
        Color resultado = colorService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdColor());
        assertEquals("Rojo", resultado.getNombreColor());
        verify(colorRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el color no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(colorRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> colorService.findById(999));
        verify(colorRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar un nuevo color")
    void testSave() {
        // Arrange
        when(colorRepository.save(colorTest)).thenReturn(colorTest);

        // Act
        Color resultado = colorService.save(colorTest);

        // Assert
        assertNotNull(resultado);
        assertEquals("Rojo", resultado.getNombreColor());
        verify(colorRepository, times(1)).save(colorTest);
    }

    @Test
    @DisplayName("Debe actualizar un color parcialmente")
    void testPatchColor() {
        // Arrange
        Color colorActualizar = new Color();
        colorActualizar.setNombreColor("Rojo Oscuro");
        
        when(colorRepository.findById(1)).thenReturn(Optional.of(colorTest));
        when(colorRepository.save(any(Color.class))).thenReturn(colorTest);

        // Act
        Color resultado = colorService.patchColor(1, colorActualizar);

        // Assert
        assertNotNull(resultado);
        assertEquals("Rojo Oscuro", resultado.getNombreColor());
        verify(colorRepository, times(1)).findById(1);
        verify(colorRepository, times(1)).save(any(Color.class));
    }

    @Test
    @DisplayName("Debe eliminar un color por ID")
    void testDeleteById() {
        // Arrange
        when(colorRepository.findById(1)).thenReturn(Optional.of(colorTest));
        doNothing().when(coloresRepository).deleteByIdColor(colorTest);
        doNothing().when(colorRepository).delete(colorTest);

        // Act
        colorService.deleteById(1);

        // Assert
        verify(colorRepository, times(1)).findById(1);
        verify(coloresRepository, times(1)).deleteByIdColor(colorTest);
        verify(colorRepository, times(1)).delete(colorTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar color inexistente")
    void testDeleteByIdNotFound() {
        // Arrange
        when(colorRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> colorService.deleteById(999));
        verify(colorRepository, times(1)).findById(999);
        verify(coloresRepository, never()).deleteByIdColor(any());
    }
}
