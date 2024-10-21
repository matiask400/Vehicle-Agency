package com.example.personasData.controllers;

import com.example.personasData.models.Persona;
import com.example.personasData.services.PersonaService;
import com.example.personasData.services.ServiceException;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

@Slf4j
@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    private final PersonaService service;

    @Autowired
    public PersonaController(PersonaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Persona>> getAllPersonas() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        try {
            Persona found = service.getById(id);
            return ResponseEntity.ok(found);
        } catch (ServiceException e) {
            return ResponseEntity.notFound()
                    .header("Error-Message", e.getMessage())
                    .build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Persona>> getPersonaByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.getByNombre(nombre));
    }


    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        return ResponseEntity.ok(service.create(persona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        try {
            Persona found = service.getById(id);
            found.setDocumento(personaDetails.getDocumento());
            found.setNombre(personaDetails.getNombre());
            found.setApellido(personaDetails.getApellido());
            found.setFechaNacimiento(personaDetails.getFechaNacimiento());
            found.setExtranjero(personaDetails.getExtranjero());
            return ResponseEntity.ok(service.update(id, found));
        } catch (ServiceException e) {
            return ResponseEntity.notFound()
                    .header("Error-Message", e.getMessage())
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> deletePersona(@PathVariable Long id) {
        try {
            Persona found = service.getById(id);
            service.delete(id);
            return ResponseEntity.ok(found);
        } catch (ServiceException e) {
            return ResponseEntity.notFound()
                    .header("Error-Message", e.getMessage())
                    .build();
        }
    }

    
}
