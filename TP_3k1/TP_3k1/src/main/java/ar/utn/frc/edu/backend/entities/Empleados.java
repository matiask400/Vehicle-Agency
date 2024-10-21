package ar.utn.frc.edu.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;

@Entity
@Table(name = "empleados")
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
    private String telefonoContacto;

    // Getters y Setters
    public Long getLegajo() {
        return legajo;
    }

    public void setLegajo(Long legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleados empleados)) return false;
        return Objects.equals(legajo, empleados.legajo) && Objects.equals(nombre, empleados.nombre) && Objects.equals(apellido, empleados.apellido) && Objects.equals(telefonoContacto, empleados.telefonoContacto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legajo, nombre, apellido, telefonoContacto);
    }

    @Override
    public String toString() {
        return "Empleados{" +
                "legajo=" + legajo +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                '}';
    }

    public Empleados() {}

    public Empleados(Long legajo, String nombre, String apellido, String telefonoContacto) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonoContacto = telefonoContacto;
    }
}
