package com.example.tpVehiculos.services;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.models.Interesados;
import com.example.tpVehiculos.models.Vehiculos;
import com.example.tpVehiculos.models.Empleados;
import com.example.tpVehiculos.repositories.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PruebaService {

    @Autowired
    private PruebasDAO pruebasDAO;

    @Autowired
    private InteresadoDAO interesadoDAO;

    @Autowired
    private VehiculosDAO vehiculosDAO;

    @Autowired
    private EmpleadosDAO empleadosDAO;

    @Autowired
    private PosicionesDAO posicionesDAO;

    @Autowired
    public PruebaService(PruebasDAO pruebasDao,
                         VehiculosDAO vehiculosDAO,
                         EmpleadosDAO empleadosDAO,
                         InteresadoDAO interesadoDAO,
                         PosicionesDAO posicionesDAO
    ){
        this.pruebasDAO = pruebasDao;
        this.vehiculosDAO = vehiculosDAO;
        this.empleadosDAO = empleadosDAO;
        this.interesadoDAO = interesadoDAO;
        this.posicionesDAO = posicionesDAO;
    }

    @Transactional
    public Pruebas crearPrueba(Long idVehiculo,
                               Long idInteresado,
                               Integer idEmpleado) {
        try {
            Interesados interesado = interesadoDAO.findById(idInteresado)
                    .orElseThrow(() -> new RuntimeException("Interesado no encontrado"));

            if (interesado.isRestringido() || interesado.getFechaVencimientoLicencia().isBefore(LocalDate.now())) {
                throw new RuntimeException("Interesado con licencia vencida o restringido.");
            }

            Vehiculos vehiculo = vehiculosDAO.findByID(idVehiculo);
            if (vehiculo == null) {
                throw new RuntimeException("Prueba no encontrada");
            }
            Empleados empleado = empleadosDAO.findByLegajo(idEmpleado);
            if (empleado == null) {
                throw new RuntimeException("Prueba no encontrada");
            }
            Pruebas pruebaActiva = pruebasDAO.findPruebaActivaByVehiculo(idVehiculo);
            if (pruebaActiva == null) {
                throw new RuntimeException("El vehiculo ya esta siendo activado");
            }
            Pruebas nuevaPrueba = new Pruebas();
            nuevaPrueba.setVehiculo(vehiculo);
            nuevaPrueba.setInteresado(interesado);
            nuevaPrueba.setEmpleado(empleado);
            nuevaPrueba.setFechaHoraInicio(LocalDateTime.now());
            nuevaPrueba.setFechaHoraFin(LocalDateTime.of(9999, 1, 1, 0, 0)); // Default end date for ongoing test
            pruebasDAO.save(nuevaPrueba);
            return nuevaPrueba;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la prueba: " + e.getMessage());
        }
    }

    public List<Pruebas> listarPruebasEnCurso(LocalDateTime fechaHora) {
        return pruebasDAO.findPruebasEnCurso(fechaHora);
    }

    @Transactional
    public void finalizarPrueba(Long idPrueba, String comentario) {
        Pruebas prueba = pruebasDAO.findByID(idPrueba);
        if (prueba == null) {
            throw new RuntimeException("Prueba no encontrada");
        }
        prueba.setComentarios(comentario);
        prueba.setFechaHoraFin(LocalDateTime.now());
        pruebasDAO.save(prueba);
    }

    public String obtenerPruebasConIncidentes() {
        List<Pruebas> pruebas = pruebasDAO.obtenerPruebasIncidente();
        StringBuilder reporte = new StringBuilder();

        // Título y fecha actual
        reporte.append("===== Reporte de Incidentes =====\n");
        reporte.append("Fecha Actual: ").append(Timestamp.from(Instant.now())).append("\n\n");

        // Lista de pruebas
        reporte.append("Pruebas:\n");
        for (Pruebas prueba : pruebas) {
            reporte.append("------------------------------\n")
                    .append("Vehículo: ").append(prueba.getVehiculo().getPatente()).append("\n")
                    .append("Interesado: ").append(prueba.getInteresado().getNombre()).append(" ") .append(prueba.getInteresado().getApellido()).append("\n")
                    .append("Empleado: ").append(prueba.getEmpleado().getNombre()).append("").append(prueba.getEmpleado().getApellido()).append("\n")
                    .append("Fecha de Inicio: ").append(prueba.getFechaHoraInicio()).append("\n")
                    .append("Fecha de Fin: ").append(prueba.getFechaHoraFin()).append("\n");
        }

        return reporte.toString();
    }


    // REPORTE II - Obtener las pruebas con incidentes por empleado
    public String obtenerPruebasConIncidentesPorLegajo(Integer legajo) {
        List<Pruebas> pruebas = empleadosDAO.obtenerPruebasIncidentePorLegajo(legajo);
        StringBuilder reporte = new StringBuilder();

        // Título y fecha actual
        reporte.append("===== Reporte de Incidentes =====\n");
        reporte.append("Empleado con Legajo: ").append(legajo).append("\n");
        reporte.append("Fecha Actual: ").append(Timestamp.from(Instant.now())).append("\n\n");

        // Información sobre las pruebas correspondientes al empleado
        reporte.append("Pruebas Correspondientes al Empleado (Legajo: ").append(legajo).append("):\n");

        // Lista de pruebas
        if (pruebas.isEmpty()) {
            reporte.append("No se encontraron incidentes para este empleado.\n");
        } else {
            for (Pruebas prueba : pruebas) {
                reporte.append("------------------------------\n")
                        .append("Patente Vehículo: ").append(prueba.getVehiculo().getPatente()).append("\n")
                        .append("Interesado: ").append(prueba.getInteresado().getNombre()).append(" ").append(prueba.getInteresado().getApellido()).append("\n")
                        .append("Empleado: ").append(prueba.getEmpleado().getNombre()).append(" ").append(prueba.getEmpleado().getApellido()).append("\n")
                        .append("Fecha de Inicio: ").append(prueba.getFechaHoraInicio()).append("\n")
                        .append("Fecha de Fin: ").append(prueba.getFechaHoraFin()).append("\n\n");
            }
        }

        return reporte.toString();
    }

    // REPORTE IV - Detalle de las pruebas para un vehiculo
    public String obtenerPruebasXVehiculo(String patente) {
        List<Pruebas> pruebas = vehiculosDAO.obtenerPruebasFinalizadasPorVehiculo(patente);
        StringBuilder reporte = new StringBuilder();

        // Título y fecha actual
        reporte.append("REPORTE DE PRUEBAS PARA EL VEHICULO: "+ patente).append("\n");
        reporte.append("Fecha Actual :").append(Timestamp.from(Instant.now())).append("\n\n");

        // Lista de pruebas
        reporte.append("Pruebas:\n");
        for (Pruebas prueba : pruebas) {
            reporte.append("VEHÍCULO: ").append(prueba.getVehiculo().getPatente()).append("\n")
                    .append("INTERESADO: ").append(prueba.getInteresado().getNombre()).append("\n")
                    .append("EMPLEADO: ").append(prueba.getEmpleado().getNombre()).append("\n")
                    .append("FECHA DE INICIO: ").append(prueba.getFechaHoraInicio()).append("\n")
                    .append("FECHA DE FIN: ").append(prueba.getFechaHoraFin()).append("\n\n");
        }

        return reporte.toString();
    }
}
