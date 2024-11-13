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
 * REST controller for managing vehicle tests in progress.
 */
@RestController
@RequestMapping("/api/vehiculos/pruebas-en-curso")
public class EnCursoController {

    @Autowired
    private PruebaService pruebaService;

    /**
     * GET /api/vehiculos/en-curso : List all vehicle tests in progress at a given date and time.
     *
     * @param fechaHora the date and time to filter the tests in progress
     * @return the ResponseEntity with status 200 (OK) and the list of tests in progress,
     *         or with status 500 (Internal Server Error) if there is an error
     */
    @GetMapping
    public ResponseEntity<?> listarPruebasEnCurso(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String fechaHora) {
        try {
            List<Pruebas> pruebasEnCurso = pruebaService.listarPruebasEnCurso(fechaHora);
            return ResponseEntity.ok(pruebasEnCurso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al listar pruebas en curso: " + e.getMessage());
        }
    }
}
// ---------------------------------------------------------------------------
// curl -X GET "http://localhost:8080/api/vehiculos/pruebas-en-curso?fechaHora=2023-10-01T10:00:00"