package com.example.tpVehiculos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "marcas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marcas {
    @Id
    @GeneratedValue (generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column (name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "marcas")
    private List<Modelos> modelos;


}
