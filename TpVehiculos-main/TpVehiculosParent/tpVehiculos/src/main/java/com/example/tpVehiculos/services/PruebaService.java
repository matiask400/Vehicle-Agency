package com.example.tpVehiculos.services;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.models.Interesados;
import com.example.tpVehiculos.models.Vehiculos;
import com.example.tpVehiculos.models.Empleados;
import com.example.tpVehiculos.repositories.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Transactional
    public Pruebas crearPrueba(Long idVehiculo, Long idInteresado, Long idEmpleado) {
        try {
            Interesados interesado = interesadoDAO.findById(idInteresado)
                    .orElseThrow(() -> new RuntimeException("Interesado no encontrado"));

            if (interesado.isRestringido() || interesado.getFechaVencimientoLicencia().isBefore(LocalDate.now())) {
                throw new RuntimeException("Interesado con licencia vencida o restringido.");
            }

            Vehiculos vehiculo = vehiculosDAO.findById(idVehiculo)
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

            Empleados empleado = empleadosDAO.findById(idEmpleado)
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

            Optional<Pruebas> pruebaActiva = pruebasDAO.findPruebaActivaByVehiculo(idVehiculo);
            if (pruebaActiva.isPresent()) {
                throw new RuntimeException("El vehículo ya está siendo probado.");
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
        Pruebas prueba = pruebasDAO.findById(idPrueba)
                .orElseThrow(() -> new RuntimeException("Prueba no encontrada"));
        prueba.setComentarios(comentario);
        prueba.setFechaHoraFin(LocalDateTime.now());
        pruebasDAO.save(prueba);
    }
}
