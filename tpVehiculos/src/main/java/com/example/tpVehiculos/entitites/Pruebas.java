package com.example.tpVehiculos.entitites;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;
import java.time.LocalDateTime;

@Entity
@Table(name = "pruebas")
public class Pruebas {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculos vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_interesado")
    private Interesados interesado;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleados empleado;

    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @Column(name = "comentarios")
    private String comentarios;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Interesados getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesados interesado) {
        this.interesado = interesado;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pruebas pruebas)) return false;
        return Objects.equals(id, pruebas.id) && Objects.equals(vehiculo, pruebas.vehiculo) && Objects.equals(interesado, pruebas.interesado) && Objects.equals(empleado, pruebas.empleado) && Objects.equals(fechaHoraInicio, pruebas.fechaHoraInicio) && Objects.equals(fechaHoraFin, pruebas.fechaHoraFin) && Objects.equals(comentarios, pruebas.comentarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehiculo, interesado, empleado, fechaHoraInicio, fechaHoraFin, comentarios);
    }

    @Override
    public String toString() {
        return "Pruebas{" +
                "id=" + id +
                ", vehiculo=" + vehiculo +
                ", interesado=" + interesado +
                ", empleado=" + empleado +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }

    public Pruebas() {}

    public Pruebas(Long id, Vehiculos vehiculo, Interesados interesado, Empleados empleado, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String comentarios) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.interesado = interesado;
        this.empleado = empleado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentarios = comentarios;
    }
}