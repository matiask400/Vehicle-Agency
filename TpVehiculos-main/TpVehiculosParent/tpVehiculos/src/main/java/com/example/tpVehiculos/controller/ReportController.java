package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReporteService reporteService;

    // i. Incidentes (pruebas donde se excedieron los límites establecidos)
    @GetMapping("/incidents")
    public List<IncidenteReporte> getIncidentReports() {
        return reporteService.getIncidentReports();
    }

    @GetMapping("/employee-incidents")
    public List<IncidentDetail> getIncidentDetailsForEmployee(@RequestParam Long employeeId) {
        return reporteService.getIncidentDetailsForEmployee(employeeId);
    }

    @GetMapping("/vehicle-kilometers")
    public double getVehicleKilometers(@RequestParam Long vehicleId, @RequestParam String startDate, @RequestParam String endDate) {
        return reporteService.getVehicleKilometers(vehicleId, startDate, endDate);
    }

    @GetMapping("/vehicle-tests")
    public List<TestDetail> getVehicleTestDetails(@RequestParam Long vehicleId) {
        return reportService.getVehicleTestDetails(vehicleId);
    }
}