package com.example.tpVehiculos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "modelos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Modelos {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marcas marca;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "modelos") //nombre coinccide con clase
    private List<Vehiculos> vehiculos;

}
