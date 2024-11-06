package com.example.tpVehiculos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoController {
    @GetMapping("/api/vehiculos/hola")
    public String holaMundo() {
        return "¡Hola, mundo!";
    }
}
