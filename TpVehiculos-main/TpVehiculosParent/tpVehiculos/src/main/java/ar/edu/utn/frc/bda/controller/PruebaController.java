package ar.edu.utn.frc.bda.controller;

import ar.edu.utn.frc.bda.controller.DTO.DTOPruebas;
import ar.edu.utn.frc.bda.models.Pruebas;
import ar.edu.utn.frc.bda.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for managing `Pruebas` related endpoints.
 */
@RestController
@RequestMapping("/api/pruebas/nueva")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    /**
     * Creates a new `Pruebas` entity.
     *
     * @param idVehiculo   the ID of the vehicle
     * @param idInteresado the ID of the interested party
     * @param idEmpleado   the ID of the employee
     * @return ResponseEntity containing the created `Pruebas` entity or an error message
     */
    @PostMapping
    public ResponseEntity<?> crearPrueba(
            @RequestParam Long idVehiculo,
            @RequestParam Long idInteresado,
            @RequestParam Integer idEmpleado) {
        try {
            Pruebas nuevaPrueba = pruebaService.crearPrueba(idVehiculo, idInteresado, idEmpleado);
            DTOPruebas DTOPruebas = new DTOPruebas(nuevaPrueba);
            return ResponseEntity.ok(DTOPruebas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al crear la prueba: " + e.getMessage());
        }
    }
}
// curl -X POST "http://localhost:8083/api/vehiculos/pruebas?idVehiculo=1&idInteresado=3&idEmpleado=3" \
