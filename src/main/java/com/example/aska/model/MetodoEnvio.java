package com.example.aska.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MetodoEnvio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMetodoEnvio;

    @Column(nullable = false)
    private String metodoEnvio;
}
