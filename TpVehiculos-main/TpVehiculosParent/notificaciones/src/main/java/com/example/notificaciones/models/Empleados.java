package com.example.notificaciones.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleados {

    @Id
    @Column(name = "legajo")
    private Long legajo; // Define legajo como la clave primaria

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono_contacto")
    private Long telefonoContacto;

    // Conexiones
    @OneToMany(mappedBy = "empleado")
    private List<Pruebas> pruebas;

    public Empleados(Long legajo) {
        this.legajo = legajo; // Constructor con el campo legajo como clave primaria
    }
}
