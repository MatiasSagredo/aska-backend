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

import com.example.aska.model.Talla;
import com.example.aska.repository.TallaRepository;
import com.example.aska.repository.TallasRepository;

@DisplayName("Pruebas unitarias para TallaService")
class TallaServiceTest {

    @Mock
    private TallaRepository tallaRepository;

    @Mock
    private TallasRepository tallasRepository;

    @InjectMocks
    private TallaService tallaService;

    private Talla tallaTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tallaTest = new Talla();
        tallaTest.setIdTalla(1);
        tallaTest.setNombreTalla("M");
    }

    @Test
    @DisplayName("Debe obtener todas las tallas")
    void testFindAll() {
        // Arrange
        Talla talla2 = new Talla();
        talla2.setIdTalla(2);
        talla2.setNombreTalla("L");
        
        List<Talla> tallas = Arrays.asList(tallaTest, talla2);
        when(tallaRepository.findAll()).thenReturn(tallas);

        // Act
        List<Talla> resultado = tallaService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("M", resultado.get(0).getNombreTalla());
        verify(tallaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener una talla por ID")
    void testFindById() {
        // Arrange
        when(tallaRepository.findById(1)).thenReturn(Optional.of(tallaTest));

        // Act
        Talla resultado = tallaService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdTalla());
        assertEquals("M", resultado.getNombreTalla());
        verify(tallaRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la talla no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(tallaRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> tallaService.findById(999));
        verify(tallaRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar una nueva talla")
    void testSave() {
        // Arrange
        when(tallaRepository.save(tallaTest)).thenReturn(tallaTest);

        // Act
        Talla resultado = tallaService.save(tallaTest);

        // Assert
        assertNotNull(resultado);
        assertEquals("M", resultado.getNombreTalla());
        verify(tallaRepository, times(1)).save(tallaTest);
    }

    @Test
    @DisplayName("Debe eliminar una talla y sus dependencias")
    void testDeleteById() {
        // Arrange
        when(tallaRepository.findById(1)).thenReturn(Optional.of(tallaTest));
        doNothing().when(tallasRepository).deleteByIdTalla(tallaTest);
        doNothing().when(tallaRepository).delete(tallaTest);

        // Act
        tallaService.deleteById(1);

        // Assert
        verify(tallaRepository, times(1)).findById(1);
        verify(tallasRepository, times(1)).deleteByIdTalla(tallaTest);
        verify(tallaRepository, times(1)).delete(tallaTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar talla inexistente")
    void testDeleteByIdNotFound() {
        // Arrange
        when(tallaRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> tallaService.deleteById(999));
        verify(tallaRepository, times(1)).findById(999);
        verify(tallasRepository, never()).deleteByIdTalla(any());
    }
}
