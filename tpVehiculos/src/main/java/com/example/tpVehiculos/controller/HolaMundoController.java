package com.example.tpVehiculos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoController {
    @GetMapping("/hola")
    public String holaMundo() {
        return "¡Hola, mundo!";
    }
}
