package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.services.PruebaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehiculos/pruebas")
public class FinalizarPruebaController {

    private final PruebaService pruebaService;

    public FinalizarPruebaController(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }

    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizarPrueba(@RequestBody FinalizarPruebaRequest request) {
        try {
            pruebaService.finalizarPrueba(request.getIdPrueba(), request.getComentario());
            return ResponseEntity.ok("Prueba finalizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al finalizar la prueba.");
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class FinalizarPruebaRequest {
        private Long idPrueba;
        private String comentario;
    }
}