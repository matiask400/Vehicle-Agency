package ar.edu.utn.frc.notificacionesVehiculos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vehiculos")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Vehiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patente")
    private String patente;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelos modelo;

    @OneToMany
    private List<Pruebas> pruebas;

    @OneToMany
    private List<Posiciones> posiciones;

    public Vehiculos(Long idVehiculo) {
    }
}
