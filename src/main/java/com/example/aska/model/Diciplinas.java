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
@Table(name = "Diciplinas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diciplinas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDiciplinas;

    @ManyToOne
    @JoinColumn(name = "idDiciplina", nullable = false)
    private Diciplina idDiciplina;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto idProducto;

}
