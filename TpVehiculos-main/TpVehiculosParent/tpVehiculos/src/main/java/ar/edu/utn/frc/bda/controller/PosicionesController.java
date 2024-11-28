package ar.edu.utn.frc.bda.controller;

import ar.edu.utn.frc.bda.controller.DTO.DTOPosicion;
import ar.edu.utn.frc.bda.models.Posiciones;
import ar.edu.utn.frc.bda.services.PosicionesService;
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

    @PostMapping("/posicion")
    public ResponseEntity<String> guardarPosicion(@RequestBody DTOPosicion posicionDTO) {
        try {
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