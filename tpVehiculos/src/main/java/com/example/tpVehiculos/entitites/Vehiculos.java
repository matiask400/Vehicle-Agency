package com.example.tpVehiculos.entitites;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;

@Entity
@Table(name = "vehiculos")
public class Vehiculos {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "patente")
    private String patente;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelos modelo;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Modelos getModelo() {
        return modelo;
    }

    public void setModelo(Modelos modelo) {
        this.modelo = modelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehiculos vehiculos)) return false;
        return Objects.equals(id, vehiculos.id) && Objects.equals(patente, vehiculos.patente) && Objects.equals(modelo, vehiculos.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patente, modelo);
    }

    @Override
    public String toString() {
        return "Vehiculos{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", modelo=" + modelo +
                '}';
    }

    public Vehiculos() {}

    public Vehiculos(Long id, String patente, Modelos modelo) {
        this.id = id;
        this.patente = patente;
        this.modelo = modelo;
    }
}
