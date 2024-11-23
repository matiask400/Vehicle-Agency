package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.models.IncidentReport;
import com.example.tpVehiculos.models.IncidentDetail;
import com.example.tpVehiculos.models.TestDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/incidents")
    public List<IncidentReport> getIncidentReports() {
        return reportService.getIncidentReports();
    }

    @GetMapping("/employee-incidents")
    public List<IncidentDetail> getIncidentDetailsForEmployee(@RequestParam Long employeeId) {
        return reportService.getIncidentDetailsForEmployee(employeeId);
    }

    @GetMapping("/vehicle-kilometers")
    public double getVehicleKilometers(@RequestParam Long vehicleId, @RequestParam String startDate, @RequestParam String endDate) {
        return reportService.getVehicleKilometers(vehicleId, startDate, endDate);
    }

    @GetMapping("/vehicle-tests")
    public List<TestDetail> getVehicleTestDetails(@RequestParam Long vehicleId) {
        return reportService.getVehicleTestDetails(vehicleId);
    }
}