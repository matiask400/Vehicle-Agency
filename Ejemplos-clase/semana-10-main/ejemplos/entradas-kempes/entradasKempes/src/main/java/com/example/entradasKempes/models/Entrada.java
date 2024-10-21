package com.example.entradasKempes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Entradas")
@Data
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apellido_nombre", nullable = false)
    private String apellidoNombre;

    @Column(name = "id_evento", nullable = false)
    private int evento;

    @Column(name = "es_socio")
    private char esSocio;
}
