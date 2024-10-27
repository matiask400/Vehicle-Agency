package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.models.Posiciones;
import com.example.tpVehiculos.services.PosicionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posiciones")
public class PosicionesController {

    private final PosicionesService posicionesService;

    public PosicionesController(PosicionesService posicionesService) {
        this.posicionesService = posicionesService;
    }

    @PostMapping
    public ResponseEntity<String> recibirPosicion(@RequestBody Posiciones posicion) {
        double radioPermitido = 1000.0;  // Ejemplo de radio en metros
        double zonaPeligrosaLat = -34.603722;
        double zonaPeligrosaLon = -58.381592;

        try {
            posicionesService.evaluarPosicion(posicion, radioPermitido, zonaPeligrosaLat, zonaPeligrosaLon);
            return ResponseEntity.ok("Posición evaluada correctamente.");
        } catch (Exception e) {
            // Loguear el error si es necesario
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al evaluar la posición.");
        }
    }
}


