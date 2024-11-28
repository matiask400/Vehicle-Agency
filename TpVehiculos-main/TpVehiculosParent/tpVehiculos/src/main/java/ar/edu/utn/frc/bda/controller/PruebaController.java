package ar.edu.utn.frc.bda.controller;

import ar.edu.utn.frc.bda.controller.DTO.DTOPruebas;
import ar.edu.utn.frc.bda.models.Pruebas;
import ar.edu.utn.frc.bda.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pruebas/nueva")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    @PostMapping
    public ResponseEntity<?> crearPrueba(
            @RequestParam Long idVehiculo,
            @RequestParam Long idInteresado,
            @RequestParam Integer idEmpleado) {
        try {
            Pruebas nuevaPrueba = pruebaService.crearPrueba(idVehiculo, idInteresado, idEmpleado);
            DTOPruebas dtoPruebas = new DTOPruebas(nuevaPrueba);
            return ResponseEntity.ok(dtoPruebas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al crear la prueba: " + e.getMessage());
        }
    }
}