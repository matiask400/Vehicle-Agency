package ar.edu.utn.frc.bda.controller;

import ar.edu.utn.frc.bda.controller.DTO.DTOReporte;
import ar.edu.utn.frc.bda.controller.DTO.DTOVehiculo;
import ar.edu.utn.frc.bda.services.PosicionesService;
import ar.edu.utn.frc.bda.services.PruebaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pruebas")
public class ReportController {
    private final PruebaService pruebaService;
    private final PosicionesService posicionService;

    public ReportController(PruebaService pruebaService,
                            PosicionesService posicionService) {
        this.pruebaService = pruebaService;
        this.posicionService = posicionService;
    }

    // 1 f 1 - Reportes de incidentes
    @GetMapping("/reportesIncidentes")
    public ResponseEntity<String> obtenerReportesIncidentes() {
        try {
            String reportes = pruebaService.obtenerPruebasConIncidentes();
            return ResponseEntity.ok(reportes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los reportes de incidentes: " + e.getMessage());
        }
    }

    // 1 f 2 - Reportes de incidentes x empleado
    @GetMapping("/reportesIncidentes/{legajoEmpleado}")
    public ResponseEntity<String> obtenerReporteIncidentesPorEmpleado(@PathVariable int legajoEmpleado) {
        String reporte = pruebaService.obtenerPruebasConIncidentesPorLegajo(legajoEmpleado);

        return ResponseEntity.ok(reporte);
    }


    // 1 f 3 - Reportes de kilometros
    @PostMapping("/reporteKm")
    public ResponseEntity<String> obtenerKilometrosXVehiculo(@RequestBody DTOVehiculo vehiculoDTO) {

        Long vehiculoId = vehiculoDTO.getId().longValue();
        DTOReporte reporteDTO = vehiculoDTO.getDtoReporte();
        LocalDateTime fechaFin = reporteDTO.getFechaFin();
        LocalDateTime fechaInicio = reporteDTO.getFechaInicio();
        System.out.println("Fecha inicio: " + fechaInicio);
        System.out.println("Fecha fin: " + fechaFin);
        System.out.println("Vehiculo id: " + vehiculoId);
        System.out.println("DTO: " + reporteDTO);


        String reporte = posicionService.obtenerCantidadKilometros(vehiculoId, fechaInicio, fechaFin);
        if (reporte.isEmpty()) {
            return ResponseEntity.ok("NO HAY PRUEBAS PARA DICHO VEHÍCULO");
        }
        return ResponseEntity.ok(reporte);
    }


    // 1 f 4 - Reportes de pruebas x Vehiculos
    @GetMapping("/reporteVehiculo")
    public ResponseEntity<String> obtenerReporteIncidentesPorVehiculo(@RequestBody DTOVehiculo vehiculoDTO) {
        String reporte = pruebaService.obtenerPruebasXVehiculo(vehiculoDTO.getPatente());

        if (reporte.isEmpty()) {
            return ResponseEntity.ok("NO HAY PRUEBAS PARA DICHO VEHICULO");
        }

        return ResponseEntity.ok(reporte);
    }


}
