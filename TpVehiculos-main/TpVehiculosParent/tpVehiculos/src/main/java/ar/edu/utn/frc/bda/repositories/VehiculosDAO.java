package com.example.tpVehiculos.repositories;


import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.models.Vehiculos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoArgsConstructor
@Repository
public class VehiculosDAO {
    @PersistenceContext
    private EntityManager em;

    // Métodos
    public Vehiculos findByPatente(String patente) {
        try {
            TypedQuery<Vehiculos> query = em.createQuery("SELECT v FROM Vehiculo v WHERE v.patente = :patente", Vehiculos.class);
            query.setParameter("patente", patente);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Métodos
    public Vehiculos findByID(Long id){
        try {
            return em.find(Vehiculos.class, id);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    //Pruebas realizadas para un vehiculo
    public List<Pruebas> obtenerPruebasFinalizadasPorVehiculo(String patente) {
        try {
            return em.createQuery("SELECT p FROM Prueba p WHERE p.fecha_hora_fin IS NOT NULL AND p.vehiculo.patente = :patente", Pruebas.class)
                    .setParameter("patente", patente)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
