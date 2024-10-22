package com.example.tpVehiculos.entitites;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;

@Entity
@Table(name = "modelos")
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

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modelos modelos)) return false;
        return Objects.equals(id, modelos.id) && Objects.equals(marca, modelos.marca) && Objects.equals(descripcion, modelos.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, descripcion);
    }

    @Override
    public String toString() {
        return "Modelos{" +
                "id=" + id +
                ", marca=" + marca +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public Modelos() {}

    public Modelos(Long id, Marcas marca, String descripcion) {
        this.id = id;
        this.marca = marca;
        this.descripcion = descripcion;
    }
}
