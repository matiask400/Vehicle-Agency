package com.example.tpVehiculos.repositories;

import com.example.tpVehiculos.models.Empleados;
import com.example.tpVehiculos.models.Pruebas;
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
            return em.createQuery("SELECT p FROM Prueba p WHERE p.inicidente = TRUE AND p.empleado.legajo = :legajo", Pruebas.class)
                    .setParameter("legajo", legajo)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

