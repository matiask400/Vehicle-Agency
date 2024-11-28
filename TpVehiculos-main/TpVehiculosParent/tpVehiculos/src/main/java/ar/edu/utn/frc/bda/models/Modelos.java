package ar.edu.utn.frc.bda.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "modelos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Modelos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marcas marca;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "modelo") //nombre coinccide con clase
    private List<Vehiculos> vehiculos;

    @Override
    public String toString() {
        return "Modelos{" +
                "id=" + id +
                ", nombre='" + descripcion + '\'' +
                ", marca=" + (marca != null ? marca.getId() : "null") +
                '}';
    }

    public Modelos(String descripcion) {
        this.descripcion = descripcion;
    }

}
