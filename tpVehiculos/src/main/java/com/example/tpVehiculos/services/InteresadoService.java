package com.example.tpVehiculos.services;

import com.example.tpVehiculos.models.Interesados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tpVehiculos.repositories.InteresadosRepository;

@Service
public class InteresadoService {
    private final InteresadosRepository repository;

    @Autowired
    public EntradaService(InteresadosRepository repository) {
        this.repository = repository;
    }
    public Interesados create(Interesados interesados) {
        return repository.save(interesados);
    }
}