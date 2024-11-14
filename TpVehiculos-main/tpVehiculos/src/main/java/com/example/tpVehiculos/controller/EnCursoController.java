package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.controller.DTO.DTOPruebas;
import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing vehicle tests in progress.
 */
@RestController
@RequestMapping("/api/vehiculos/encurso")
public class EnCursoController {

    @Autowired
    private PruebaService pruebaService;

    /**
     * GET /api/vehiculos/encurso : List all vehicle tests in progress at a given date and time.
     *
     * @param fechaHora the date and time to filter the tests in progress
     * @return the ResponseEntity with status 200 (OK) and the list of tests in progress,
     *         or with status 500 (Internal Server Error) if there is an error
     */
    @GetMapping
    public ResponseEntity<?> listarPruebasEnCurso(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora) {
        try {
            List<Pruebas> pruebasEnCurso = pruebaService.listarPruebasEnCurso(fechaHora);
            List<DTOPruebas> dtoPruebasEnCurso = pruebasEnCurso.stream()
                    .map(DTOPruebas::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoPruebasEnCurso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al listar pruebas en curso: " + e.getMessage());
        }
    }
}
// ---------------------------------------------------------------------------
// curl -X GET "http://localhost:8080/api/vehiculos/encurso?fechaHora=2023-10-01T10:00:00"