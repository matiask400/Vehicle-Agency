package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.controller.DTO.DTOPosicion;
import com.example.tpVehiculos.models.Posiciones;
import com.example.tpVehiculos.services.PosicionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/pruebas")
public class PosicionesController {

    private final PosicionesService posicionService;

    public PosicionesController(PosicionesService posicionService) {

        this.posicionService = posicionService;
    }

    // 4 - Endpoint para guardar una posicion
    @PostMapping("/posicion")
    public ResponseEntity<String> guardarPosicion(@RequestBody DTOPosicion posicionDTO) {
        try {
            // Llama al servicio para crear una nueva posición
            Posiciones posicion = posicionService.crearNuevaPosicion(
                    posicionDTO.getVehiculoId(),
                    posicionDTO.getLongitud(),
                    posicionDTO.getLatitud()
            );
            return ResponseEntity.ok("Posición guardada exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la posición.");
        }
    }
}
