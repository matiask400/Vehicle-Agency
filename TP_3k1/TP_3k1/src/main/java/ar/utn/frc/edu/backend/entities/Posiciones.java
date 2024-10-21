package ar.utn.frc.edu.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;
import java.time.LocalDateTime;

@Entity
@Table(name = "posiciones")
public class Posiciones {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculos vehiculo;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posiciones posiciones)) return false;
        return Objects.equals(id, posiciones.id) && Objects.equals(vehiculo, posiciones.vehiculo) && Objects.equals(fechaHora, posiciones.fechaHora) && Objects.equals(latitud, posiciones.latitud) && Objects.equals(longitud, posiciones.longitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehiculo, fechaHora, latitud, longitud);
    }

    @Override
    public String toString() {
        return "Posiciones{" +
                "id=" + id +
                ", vehiculo=" + vehiculo +
                ", fechaHora=" + fechaHora +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }

    public Posiciones() {}

    public Posiciones(Long id, Vehiculos vehiculo, LocalDateTime fechaHora, Double latitud, Double longitud) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.fechaHora = fechaHora;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
