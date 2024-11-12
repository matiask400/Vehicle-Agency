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

    /**
     * Lists all `Pruebas` entities that are currently in progress.
     *
     * @param fechaHora the date and time to filter the `Pruebas` entities
     * @return ResponseEntity containing the list of `Pruebas` entities in progress or an error message
     */
    @GetMapping("/en-curso")
    public ResponseEntity<?> listarPruebasEnCurso(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora) {
        try {
            List<Pruebas> pruebasEnCurso = pruebaService.listarPruebasEnCurso(fechaHora);
            return ResponseEntity.ok(pruebasEnCurso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al listar pruebas en curso: " + e.getMessage());
        }
    }
}

// curl -X POST "http://localhost:8080/api/vehiculos/pruebas" \
//     -d "idVehiculo=1" \
//     -d "idInteresado=2" \
//     -d "idEmpleado=3"
// ---------------------------------------------------------------------------
// curl -X GET "http://localhost:8080/api/vehiculos/pruebas/en-curso" \
//     -d "fechaHora=2023-10-01T10:00:00"