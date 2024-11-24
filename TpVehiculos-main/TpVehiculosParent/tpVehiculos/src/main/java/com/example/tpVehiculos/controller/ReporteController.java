package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // i. Reporte de incidentes
    @GetMapping("/incidentes")
    public List<Pruebas> obtenerIncidentes() {
        return reporteService.getIncidentReports();
    }

    // ii. Detalle de incidentes por empleado
    @GetMapping("/incidentes/empleado/{idEmpleado}")
    public List<Pruebas> obtenerIncidentesPorEmpleado(@PathVariable Long idEmpleado) {
        return reporteService.obtenerIncidentesPorEmpleado(idEmpleado);
    }

    // iii. Kilómetros recorridos por un vehículo en un período
    @GetMapping("/kilometros")
    public double obtenerKilometros(@RequestParam Long idVehiculo,
                                    @RequestParam String inicio,
                                    @RequestParam String fin) {
        LocalDateTime fechaInicio = LocalDateTime.parse(inicio);
        LocalDateTime fechaFin = LocalDateTime.parse(fin);
        return reporteService.obtenerKilometrosRecorridos(idVehiculo, fechaInicio, fechaFin);
    }

    // iv. Detalle de pruebas realizadas para un vehículo
    @GetMapping("/pruebas/vehiculo/{idVehiculo}")
    public List<Pruebas> obtenerPruebasPorVehiculo(@PathVariable Long idVehiculo) {
        return reporteService.obtenerPruebasPorVehiculo(idVehiculo);
    }
}
