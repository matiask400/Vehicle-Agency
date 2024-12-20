package ar.edu.utn.frc.bda.controller;

import ar.edu.utn.frc.bda.services.PruebaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 1 c
@RestController
@RequestMapping("/api/pruebas")
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