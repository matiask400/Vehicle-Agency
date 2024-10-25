package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * Endpoint para listar todas las pruebas en curso en un momento dado.
     * @param fechaHora Fecha y hora para verificar las pruebas en curso.
     * @return Lista de pruebas en curso o mensaje de error en caso de excepción.
     */
    @GetMapping("/en-curso")
    public ResponseEntity<?> listarPruebasEnCurso(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora) {
        try {
            List<Pruebas> pruebasEnCurso = pruebaService.listarPruebasEnCurso(fechaHora);
            return ResponseEntity.ok(pruebasEnCurso);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al listar pruebas en curso: " + e.getMessage());
        }
    }
}
