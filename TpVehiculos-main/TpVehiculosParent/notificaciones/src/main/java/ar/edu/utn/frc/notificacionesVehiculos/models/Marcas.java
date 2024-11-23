package ar.edu.utn.frc.notificacionesVehiculos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "marcas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marcas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "marca")
    private List<Modelos> modelos;


}
