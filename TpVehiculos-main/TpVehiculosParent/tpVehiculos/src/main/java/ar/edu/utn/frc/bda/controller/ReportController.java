package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.controller.DTO.DTOVehiculo;
import com.example.tpVehiculos.services.PosicionesService;
import com.example.tpVehiculos.services.PruebaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    // 6a - Reportes de incidentes
    @GetMapping("/reportesIncidentes")
    public ResponseEntity<String> obtenerReportesIncidentes() {
        try {
            // Llamada al servicio para obtener las pruebas como un String
            String reportes = pruebaService.obtenerPruebasConIncidentes();
            // Retorna el reporte con un código HTTP 200
            return ResponseEntity.ok(reportes);
        } catch (Exception e) {
            // Manejo de errores, retorna un mensaje con código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los reportes de incidentes.");
        }
    }

    //6b - Reportes de incidentes x empleado
    @GetMapping("/reportesIncidentes/{legajoEmpleado}")
    public ResponseEntity<String> obtenerReporteIncidentesPorEmpleado(@PathVariable int legajoEmpleado) {
        String reporte = pruebaService.obtenerPruebasConIncidentesPorLegajo(legajoEmpleado);

        return ResponseEntity.ok(reporte);
    }


    // 6c - Reportes de kilometros
    @PostMapping("/reporteKm")
    public ResponseEntity<String> obtenerKilometrosXVehiculo(@RequestBody DTOVehiculo vehiculoDTO) {

        Long vehiculoId = vehiculoDTO.getId().longValue();
        org.example.tpi_134.DTOS.DTOReporte reporteDTO = vehiculoDTO.getDtoReporte();
        LocalDateTime fechaFin = reporteDTO.getFechaFin().toLocalDateTime();
        LocalDateTime fechaInicio = reporteDTO.getFechaInicio().toLocalDateTime();
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


    //6d - Reportes de pruebas x Vehiculos
    @GetMapping("/reporteVehiculo")
    public ResponseEntity<String> obtenerReporteIncidentesPorVehiculo(@RequestBody DTOVehiculo vehiculoDTO) {
        String reporte = pruebaService.obtenerPruebasXVehiculo(vehiculoDTO.getPatente());

        if (reporte.isEmpty()) {
            return ResponseEntity.ok("NO HAY PRUEBAS PARA DICHO VEHICULO");
        }

        return ResponseEntity.ok(reporte);
    }


}
