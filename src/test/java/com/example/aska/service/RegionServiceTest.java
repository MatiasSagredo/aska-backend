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

import com.example.aska.model.Region;
import com.example.aska.repository.ComunaRepository;
import com.example.aska.repository.RegionRepository;

@DisplayName("Pruebas unitarias para RegionService")
class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private ComunaRepository comunaRepository;

    @InjectMocks
    private RegionService regionService;

    private Region regionTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        regionTest = new Region();
        regionTest.setIdRegion(1);
        regionTest.setNombreRegion("Región Metropolitana");
    }

    @Test
    @DisplayName("Debe obtener todas las regiones")
    void testFindAll() {
        // Arrange
        Region region2 = new Region();
        region2.setIdRegion(2);
        region2.setNombreRegion("Región del Bío Bío");
        
        List<Region> regiones = Arrays.asList(regionTest, region2);
        when(regionRepository.findAll()).thenReturn(regiones);

        // Act
        List<Region> resultado = regionService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Región Metropolitana", resultado.get(0).getNombreRegion());
        verify(regionRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener una región por ID")
    void testFindById() {
        // Arrange
        when(regionRepository.findById(1)).thenReturn(Optional.of(regionTest));

        // Act
        Region resultado = regionService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdRegion());
        assertEquals("Región Metropolitana", resultado.getNombreRegion());
        verify(regionRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la región no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(regionRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> regionService.findById(999));
        verify(regionRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar una nueva región")
    void testSave() {
        // Arrange
        when(regionRepository.save(regionTest)).thenReturn(regionTest);

        // Act
        Region resultado = regionService.save(regionTest);

        // Assert
        assertNotNull(resultado);
        assertEquals("Región Metropolitana", resultado.getNombreRegion());
        verify(regionRepository, times(1)).save(regionTest);
    }

    @Test
    @DisplayName("Debe eliminar una región y sus dependencias")
    void testDeleteById() {
        // Arrange
        when(regionRepository.findById(1)).thenReturn(Optional.of(regionTest));
        doNothing().when(comunaRepository).deleteByIdRegion(regionTest);
        doNothing().when(regionRepository).delete(regionTest);

        // Act
        regionService.deleteById(1);

        // Assert
        verify(regionRepository, times(1)).findById(1);
        verify(comunaRepository, times(1)).deleteByIdRegion(regionTest);
        verify(regionRepository, times(1)).delete(regionTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar región inexistente")
    void testDeleteByIdNotFound() {
        // Arrange
        when(regionRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> regionService.deleteById(999));
        verify(regionRepository, times(1)).findById(999);
        verify(comunaRepository, never()).deleteByIdRegion(any());
    }
}
