package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Rest controller for managing `Pruebas` related endpoints.
 */
@RestController
@RequestMapping("/api/vehiculos/pruebas")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    /**
     * Creates a new `Pruebas` entity.
     *
     * @param idVehiculo   the ID of the vehicle
     * @param idInteresado the ID of the interested party
     * @param idEmpleado   the ID of the employee
     * @return ResponseEntity containing the created `Pruebas` entity or an error message
     */
    @PostMapping
    public ResponseEntity<?> crearPrueba(
            @RequestParam Long idVehiculo,
            @RequestParam Long idInteresado,
            @RequestParam Long idEmpleado) {
        try {
            Pruebas nuevaPrueba = pruebaService.crearPrueba(idVehiculo, idInteresado, idEmpleado);
            return ResponseEntity.ok(nuevaPrueba);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al crear la prueba: " + e.getMessage());
        }
    }
}
// curl -X POST "http://localhost:8083/api/vehiculos/pruebas?idVehiculo=1&idInteresado=3&idEmpleado=3" \
