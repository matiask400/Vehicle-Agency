package com.example.personasData.services;

import com.example.personasData.models.Persona;
import com.example.personasData.repositories.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    private final PersonaRepository repository;
    
    @Autowired
    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }
 
    public Iterable<Persona> getAll() {
        return repository.findAll();
    }

    public Persona getById(Long id) throws ServiceException {
        return repository.findById(id).orElseThrow(()-> 
                new ServiceException("Persona no encontrada"));
    }

    public List<Persona> getByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
    
    
    public Persona create(Persona persona) {
        return repository.save(persona);
    }

    public Persona update(Long id, Persona personaDetails) throws ServiceException {
        Persona persona = repository.findById(id)
                .orElseThrow(() -> new ServiceException("Persona no encontrada"));

        persona.setDocumento(personaDetails.getDocumento());
        persona.setNombre(personaDetails.getNombre());
        persona.setApellido(personaDetails.getApellido());
        persona.setFechaNacimiento(personaDetails.getFechaNacimiento());
        persona.setExtranjero(personaDetails.getExtranjero());

        return repository.save(persona);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}