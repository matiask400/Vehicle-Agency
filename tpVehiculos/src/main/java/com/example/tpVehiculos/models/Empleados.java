package com.example.tpVehiculos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Empleados {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long legajo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono_contacto")
    private Long telefonoContacto;

    // Conexiones

    @OneToMany(mappedBy = "empleado")
    private List<Pruebas> pruebas;


    public Empleados(Long idEmpleado) {
    }
}




