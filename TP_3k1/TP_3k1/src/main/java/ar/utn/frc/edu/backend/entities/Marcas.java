package ar.utn.frc.edu.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Table (name = "marcas")

public class Marcas {
    @Id
    @GeneratedValue (generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column (name = "nombre")
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marcas marcas)) return false;
        return Objects.equals(id, marcas.id) && Objects.equals(nombre, marcas.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "Marcas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
    public Marcas () {}
    public Marcas(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
