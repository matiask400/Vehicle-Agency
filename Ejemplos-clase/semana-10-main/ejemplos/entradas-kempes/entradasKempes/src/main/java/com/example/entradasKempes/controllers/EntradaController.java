package com.example.entradasKempes.controllers;

import com.example.entradasKempes.dtos.EntradaDto;
import com.example.entradasKempes.models.Entrada;
import com.example.entradasKempes.services.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/entradas")
public class EntradaController {
    private final EntradaService service;

    @Autowired
    public EntradaController(EntradaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Entrada> createEntrada(@RequestBody EntradaDto entrada) {
        
        Entrada nuevo = new Entrada();
        nuevo.setEvento(entrada.getEvento().getId_evento());
        nuevo.setEsSocio(entrada.getPersona().getEsSocio().charAt(0));
        nuevo.setApellidoNombre(entrada.getPersona().getNombreCompleto());
        
        return ResponseEntity.ok(service.create(nuevo));
    }


}
