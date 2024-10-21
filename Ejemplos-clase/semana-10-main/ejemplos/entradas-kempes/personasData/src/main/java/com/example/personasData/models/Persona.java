package com.example.personasData.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Personas")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long documento;
    private String nombre;
    private String apellido;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaNacimiento;

    private char extranjero;
}
