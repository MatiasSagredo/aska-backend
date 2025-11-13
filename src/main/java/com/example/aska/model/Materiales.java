package com.example.aska.model;

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
@Table(name = "Materiales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Materiales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMateriales;

    @ManyToOne
    @JoinColumn(name = "idMaterial", nullable = false)
    private Material idMaterial;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto idProducto;

}
