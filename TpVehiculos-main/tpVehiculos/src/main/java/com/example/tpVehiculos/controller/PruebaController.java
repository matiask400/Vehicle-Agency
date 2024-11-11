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
@RequestMapping("/api/vehiculos/pruebas")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

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
