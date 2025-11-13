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
@Table(name = "Imagenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imagenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagen;

    @Column(nullable = false)
    private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto idProducto;

}
