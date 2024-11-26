package ar.edu.utn.frc.bda.repositories;

import ar.edu.utn.frc.bda.models.Pruebas;
import ar.edu.utn.frc.bda.services.ConfiguracionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PruebasDAO {

    @PersistenceContext
    private EntityManager em;

    private final ConfiguracionService configuracionService;

    public Pruebas findPruebaActivaByVehiculo(Long idVehiculo) {
        return em.createQuery(
                "SELECT p FROM Pruebas p WHERE p.vehiculo.id = :idVehiculo AND p.fechaHoraFin IS NULL",
                Pruebas.class
        ).setParameter("idVehiculo", idVehiculo)
         .getResultStream()
         .findFirst()
         .orElse(null);
    }

    public List<Pruebas> findPruebasEnCurso(LocalDateTime fechaHora) {
        return em.createQuery(
                "SELECT p FROM Pruebas p WHERE p.fechaHoraInicio <= :fechaHora AND (p.fechaHoraFin IS NULL OR p.fechaHoraFin > :fechaHora)",
                Pruebas.class
        ).setParameter("fechaHora", fechaHora)
         .getResultList();
    }

    public List<Pruebas> findByEmpleadoLegajo(Long legajoEmpleado) {
        return em.createQuery(
                "SELECT p FROM Pruebas p WHERE p.empleado.legajo = :legajoEmpleado",
                Pruebas.class
        ).setParameter("legajoEmpleado", legajoEmpleado)
         .getResultList();
    }

    public List<Pruebas> findByEmpleadoLegajoAndExcesoLimite(Long legajoEmpleado) {
        return em.createQuery(
                "SELECT p FROM Pruebas p WHERE p.empleado.legajo = :legajoEmpleado AND p.excesoLimite = true",
                Pruebas.class
        ).setParameter("legajoEmpleado", legajoEmpleado)
         .getResultList();
    }

    public List<Pruebas> findAllEnCurso() {
        return em.createQuery(
                "SELECT p FROM Pruebas p WHERE p.fechaHoraFin IS NULL",
                Pruebas.class
        ).getResultList();
    }

    public List<Pruebas> obtenerPruebasIncidente() {
        return em.createQuery(
                "SELECT p FROM Pruebas p WHERE p.estado IS NULL",
                Pruebas.class
        ).getResultList();
    }

    @Transactional
    public Pruebas save(Pruebas prueba) {
        em.persist(prueba);
        return prueba;
    }

    public Pruebas findByID(Long id) {
        return em.find(Pruebas.class, id);
    }

    @Transactional
    public void finalizarPruebaEnCurso(Long id, String comentario) {
        Pruebas prueba = findByID(id);
        if (prueba != null) {
            prueba.setFechaHoraFin(LocalDateTime.now());
            if (comentario != null) {
                prueba.setComentarios(comentario);
            }
            em.merge(prueba);
        }
    }
}