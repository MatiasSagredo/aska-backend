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

import com.example.aska.model.Venta;
import com.example.aska.repository.ProductosVentaRepository;
import com.example.aska.repository.VentaRepository;

@DisplayName("Pruebas unitarias para VentaService")
class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @Mock
    private ProductosVentaRepository productosVentaRepository;

    @InjectMocks
    private VentaService ventaService;

    private Venta ventaTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ventaTest = new Venta();
        ventaTest.setIdVenta(1);
        ventaTest.setTotal(100);
    }

    @Test
    @DisplayName("Debe obtener todas las ventas")
    void testFindAll() {
        // Arrange
        Venta venta2 = new Venta();
        venta2.setIdVenta(2);
        venta2.setTotal(250);
        
        List<Venta> ventas = Arrays.asList(ventaTest, venta2);
        when(ventaRepository.findAll()).thenReturn(ventas);

        // Act
        List<Venta> resultado = ventaService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(100, (int) resultado.get(0).getTotal());
        verify(ventaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener una venta por ID")
    void testFindById() {
        // Arrange
        when(ventaRepository.findById(1)).thenReturn(Optional.of(ventaTest));

        // Act
        Venta resultado = ventaService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdVenta());
        assertEquals(100, (int) resultado.getTotal());
        verify(ventaRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la venta no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(ventaRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> ventaService.findById(999));
        verify(ventaRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar una nueva venta")
    void testSave() {
        // Arrange
        when(ventaRepository.save(ventaTest)).thenReturn(ventaTest);

        // Act
        Venta resultado = ventaService.save(ventaTest);

        // Assert
        assertNotNull(resultado);
        assertEquals(100, (int) resultado.getTotal());
        verify(ventaRepository, times(1)).save(ventaTest);
    }

    @Test
    @DisplayName("Debe actualizar una venta parcialmente")
    void testPatchVenta() {
        // Arrange
        Venta ventaActualizar = new Venta();
        ventaActualizar.setTotal(150);
        
        Venta ventaActualizada = new Venta();
        ventaActualizada.setIdVenta(1);
        ventaActualizada.setTotal(150);
        
        when(ventaRepository.findById(1)).thenReturn(Optional.of(ventaTest));
        when(ventaRepository.save(any(Venta.class))).thenReturn(ventaActualizada);

        // Act
        Venta resultado = ventaService.patchVenta(1, ventaActualizar);

        // Assert
        assertNotNull(resultado);
        assertEquals(150, (int) resultado.getTotal());
        verify(ventaRepository, times(1)).findById(1);
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    @DisplayName("Debe eliminar una venta y sus dependencias")
    void testDeleteById() {
        // Arrange
        when(ventaRepository.findById(1)).thenReturn(Optional.of(ventaTest));
        doNothing().when(productosVentaRepository).deleteByIdVenta(ventaTest);
        doNothing().when(ventaRepository).delete(ventaTest);

        // Act
        ventaService.deleteById(1);

        // Assert
        verify(ventaRepository, times(1)).findById(1);
        verify(productosVentaRepository, times(1)).deleteByIdVenta(any());
        verify(ventaRepository, times(1)).delete(ventaTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar venta inexistente")
    void testDeleteByIdNotFound() {
        // Arrange
        when(ventaRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> ventaService.deleteById(999));
        verify(ventaRepository, times(1)).findById(999);
        verify(productosVentaRepository, never()).deleteByIdVenta(any());
    }

    @Test
    @DisplayName("Debe actualizar solo el total cuando es proporcionado")
    void testPatchVentaOnlyTotal() {
        // Arrange
        Venta ventaActualizar = new Venta();
        ventaActualizar.setTotal(200);
        
        Venta ventaActualizada = new Venta();
        ventaActualizada.setIdVenta(1);
        ventaActualizada.setTotal(200);
        
        when(ventaRepository.findById(1)).thenReturn(Optional.of(ventaTest));
        when(ventaRepository.save(any(Venta.class))).thenReturn(ventaActualizada);

        // Act
        Venta resultado = ventaService.patchVenta(1, ventaActualizar);

        // Assert
        assertNotNull(resultado);
        assertEquals(200, (int) resultado.getTotal());
        verify(ventaRepository, times(1)).findById(1);
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }
}
