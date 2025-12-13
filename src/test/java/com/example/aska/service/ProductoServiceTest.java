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

import com.example.aska.model.Producto;
import com.example.aska.repository.ColoresRepository;
import com.example.aska.repository.DiciplinasRepository;
import com.example.aska.repository.GradosRepository;
import com.example.aska.repository.ImagenesRepository;
import com.example.aska.repository.MaterialesRepository;
import com.example.aska.repository.ProductoRepository;
import com.example.aska.repository.ProductosVentaRepository;

@DisplayName("Pruebas unitarias para ProductoService")
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ColoresRepository coloresRepository;

    @Mock
    private MaterialesRepository materialesRepository;

    @Mock
    private DiciplinasRepository diciplinasRepository;

    @Mock
    private GradosRepository gradosRepository;

    @Mock
    private ImagenesRepository imagenesRepository;

    @Mock
    private ProductosVentaRepository productosVentaRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto productoTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productoTest = new Producto();
        productoTest.setIdProducto(1);
        productoTest.setNombreProducto("Uniforme Escolar");
        productoTest.setDescripcion("Uniforme completo");
    }

    @Test
    @DisplayName("Debe obtener todos los productos")
    void testFindAll() {
        // Arrange
        Producto producto2 = new Producto();
        producto2.setIdProducto(2);
        producto2.setNombreProducto("Pantalon");
        
        List<Producto> productos = Arrays.asList(productoTest, producto2);
        when(productoRepository.findAll()).thenReturn(productos);

        // Act
        List<Producto> resultado = productoService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Uniforme Escolar", resultado.get(0).getNombreProducto());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener un producto por ID")
    void testFindById() {
        // Arrange
        when(productoRepository.findById(1)).thenReturn(Optional.of(productoTest));

        // Act
        Producto resultado = productoService.findById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdProducto());
        assertEquals("Uniforme Escolar", resultado.getNombreProducto());
        verify(productoRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el producto no existe")
    void testFindByIdNotFound() {
        // Arrange
        when(productoRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> productoService.findById(999));
        verify(productoRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Debe guardar un nuevo producto")
    void testSave() {
        // Arrange
        when(productoRepository.save(productoTest)).thenReturn(productoTest);

        // Act
        Producto resultado = productoService.save(productoTest);

        // Assert
        assertNotNull(resultado);
        assertEquals("Uniforme Escolar", resultado.getNombreProducto());
        verify(productoRepository, times(1)).save(productoTest);
    }

    @Test
    @DisplayName("Debe actualizar un producto parcialmente")
    void testPatchProducto() {
        // Arrange
        Producto productoActualizar = new Producto();
        productoActualizar.setNombreProducto("Uniforme Deportivo");
        productoActualizar.setDescripcion("Uniforme para educación física");
        
        when(productoRepository.findById(1)).thenReturn(Optional.of(productoTest));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoTest);

        // Act
        Producto resultado = productoService.patchProducto(1, productoActualizar);

        // Assert
        assertNotNull(resultado);
        assertEquals("Uniforme Deportivo", resultado.getNombreProducto());
        assertEquals("Uniforme para educación física", resultado.getDescripcion());
        verify(productoRepository, times(1)).findById(1);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("Debe eliminar un producto y sus dependencias")
    void testDeleteById() {
        // Arrange
        when(productoRepository.findById(1)).thenReturn(Optional.of(productoTest));
        doNothing().when(coloresRepository).deleteByIdProducto(productoTest);
        doNothing().when(materialesRepository).deleteByIdProducto(productoTest);
        doNothing().when(diciplinasRepository).deleteByIdProducto(productoTest);
        doNothing().when(gradosRepository).deleteByIdProducto(productoTest);
        doNothing().when(imagenesRepository).deleteByIdProducto(productoTest);
        doNothing().when(productosVentaRepository).deleteByIdProducto(productoTest);
        doNothing().when(productoRepository).delete(productoTest);

        // Act
        productoService.deleteById(1);

        // Assert
        verify(productoRepository, times(1)).findById(1);
        verify(coloresRepository, times(1)).deleteByIdProducto(productoTest);
        verify(materialesRepository, times(1)).deleteByIdProducto(productoTest);
        verify(diciplinasRepository, times(1)).deleteByIdProducto(productoTest);
        verify(gradosRepository, times(1)).deleteByIdProducto(productoTest);
        verify(imagenesRepository, times(1)).deleteByIdProducto(productoTest);
        verify(productosVentaRepository, times(1)).deleteByIdProducto(productoTest);
        verify(productoRepository, times(1)).delete(productoTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar producto inexistente")
    void testDeleteByIdNotFound() {
        // Arrange
        when(productoRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productoService.deleteById(999));
        verify(productoRepository, times(1)).findById(999);
        verify(coloresRepository, never()).deleteByIdProducto(any());
    }

    @Test
    @DisplayName("Debe actualizar solo el nombre cuando el nombre es proporcionado")
    void testPatchProductoOnlyName() {
        // Arrange
        Producto productoActualizar = new Producto();
        productoActualizar.setNombreProducto("Nuevo Nombre");
        
        when(productoRepository.findById(1)).thenReturn(Optional.of(productoTest));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoTest);

        // Act
        productoService.patchProducto(1, productoActualizar);

        // Assert
        verify(productoRepository, times(1)).findById(1);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }
}
