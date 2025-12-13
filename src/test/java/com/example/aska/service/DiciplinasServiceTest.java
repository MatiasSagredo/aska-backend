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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.aska.model.Diciplinas;
import com.example.aska.repository.DiciplinasRepository;

@DisplayName("Pruebas unitarias para DiciplinasService")
class DiciplinasServiceTest {

    @Mock
    private DiciplinasRepository diciplinasRepository;

    @InjectMocks
    private DiciplinasService diciplinasService;

    private Diciplinas diciplinasTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        diciplinasTest = new Diciplinas();
        diciplinasTest.setIdDiciplinas(1);
    }

    @Test
    @DisplayName("Debe obtener todas las disciplinas")
    void testFindAll() {
        // Arrange
        Diciplinas diciplinas2 = new Diciplinas();
        diciplinas2.setIdDiciplinas(2);
        
        List<Diciplinas> disciplinasList = Arrays.asList(diciplinasTest, diciplinas2);
        when(diciplinasRepository.findAll()).thenReturn(disciplinasList);

        // Act
        List<Diciplinas> resultado = diciplinasService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(diciplinasRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener una disciplina por ID")
    void testFindById() {
        // Arrange
        when(diciplinasRepository.findById(1)).thenReturn(Optional.of(diciplinasTest));

        // Act
        Diciplinas resultado = diciplinasService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdDiciplinas());
        verify(diciplinasRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la disciplina no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(diciplinasRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> diciplinasService.findById(999));
        verify(diciplinasRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar una nueva disciplina")
    void testSave() {
        // Arrange
        when(diciplinasRepository.save(diciplinasTest)).thenReturn(diciplinasTest);

        // Act
        Diciplinas resultado = diciplinasService.save(diciplinasTest);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdDiciplinas());
        verify(diciplinasRepository, times(1)).save(diciplinasTest);
    }

    @Test
    @DisplayName("Debe eliminar una disciplina por ID")
    void testDeleteById() {
        // Arrange
        doNothing().when(diciplinasRepository).deleteById(1);

        // Act
        diciplinasService.deleteById(1);

        // Assert
        verify(diciplinasRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Debe guardar una disciplina con valores null")
    void testSaveWithNullValues() {
        // Arrange
        Diciplinas diciplinasNull = new Diciplinas();
        when(diciplinasRepository.save(diciplinasNull)).thenReturn(diciplinasNull);

        // Act
        Diciplinas resultado = diciplinasService.save(diciplinasNull);

        // Assert
        assertNotNull(resultado);
        verify(diciplinasRepository, times(1)).save(diciplinasNull);
    }
}
