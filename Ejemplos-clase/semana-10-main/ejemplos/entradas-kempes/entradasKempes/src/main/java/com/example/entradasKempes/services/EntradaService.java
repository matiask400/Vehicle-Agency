package com.example.entradasKempes.services;

import com.example.entradasKempes.models.Entrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entradasKempes.repositories.EntradaRepository;

@Service
public class EntradaService {
    private final EntradaRepository repository;
    
    @Autowired
    public EntradaService(EntradaRepository repository) {
        this.repository = repository;
    }
    public Entrada create(Entrada entrada) {
        return repository.save(entrada);
    }
}