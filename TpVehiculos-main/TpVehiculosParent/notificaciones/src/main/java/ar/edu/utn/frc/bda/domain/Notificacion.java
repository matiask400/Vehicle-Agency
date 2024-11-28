package ar.edu.utn.frc.bda.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mensaje;
    private String tipo;

    public Notificacion(String mensaje, String tipo) {
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "mensaje='" + mensaje + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
