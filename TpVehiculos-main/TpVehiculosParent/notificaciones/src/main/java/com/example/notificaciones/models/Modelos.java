package com.example.notificaciones.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "modelos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Modelos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marcas marca;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "modelo") //nombre coinccide con clase
    private List<Vehiculos> vehiculos;

}
