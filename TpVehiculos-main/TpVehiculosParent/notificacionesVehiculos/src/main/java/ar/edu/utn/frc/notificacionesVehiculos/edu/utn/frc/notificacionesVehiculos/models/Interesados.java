package ar.edu.utn.frc.notificacionesVehiculos.edu.utn.frc.notificacionesVehiculos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "interesados")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Interesados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "documento")
    private String documento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "restringido")
    private boolean restringido;

    @Column(name = "nro_licencia")
    private Long nroLicencia;

    @Column(name = "fecha_vencimiento_licencia")
    private LocalDate fechaVencimientoLicencia;

    // relaciones
    @OneToMany(mappedBy = "interesado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pruebas> pruebas;

}
