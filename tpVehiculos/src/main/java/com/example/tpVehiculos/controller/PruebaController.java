package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    /**
     * Endpoint para crear una nueva prueba.
     * @param idVehiculo ID del vehículo a probar.
     * @param idInteresado ID del interesado que realizará la prueba.
     * @param idEmpleado ID del empleado que supervisará la prueba.
     * @return La prueba creada o un mensaje de error si falla alguna validación.
     */
    @PostMapping
    public ResponseEntity<String> crearPrueba(
            @RequestParam Long idVehiculo,
            @RequestParam Long idInteresado,
            @RequestParam Long idEmpleado) {
        try {
            Pruebas nuevaPrueba = pruebaService.crearPrueba(idVehiculo, idInteresado, idEmpleado);
            return ResponseEntity.ok(nuevaPrueba.toString());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }
}

