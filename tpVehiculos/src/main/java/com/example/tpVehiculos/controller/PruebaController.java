package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearPrueba(@RequestParam Long clienteId, @RequestParam String patenteVehiculo) {
        String resultado = pruebaService.crearPrueba(clienteId, patenteVehiculo);
        if (resultado.equals("Prueba creada exitosamente.")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }
}

