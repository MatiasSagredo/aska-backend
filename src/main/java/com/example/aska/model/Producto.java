package com.example.aska.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "Producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Integer idProducto;

    @Column (nullable = false)
    private String nombreProducto;
    
    @Column (nullable = false)
    private String descripcion;


    @ManyToOne 
    @JoinColumn(name = "idMarca", nullable = false)
    private  Marca idMarca;
}