package ar.edu.utn.frc.bda.repositories;

import ar.edu.utn.frc.bda.models.Empleados;
import ar.edu.utn.frc.bda.models.Pruebas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoArgsConstructor
@Repository
public class EmpleadosDAO {
    @PersistenceContext
    private EntityManager em;

    // Métodos
    public Empleados findByLegajo(Integer legajo){
        try {
            return em.find(Empleados.class, legajo);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Pruebas> obtenerPruebasIncidentePorLegajo(Integer legajo) {
        try {
            return em.createQuery("SELECT p FROM Pruebas p WHERE p.estado = TRUE AND p.empleado.legajo = :legajo", Pruebas.class)
                    .setParameter("legajo", legajo)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

