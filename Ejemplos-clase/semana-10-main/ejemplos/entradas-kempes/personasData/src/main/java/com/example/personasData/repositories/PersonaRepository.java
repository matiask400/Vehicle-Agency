package com.example.personasData.repositories;

import com.example.personasData.models.Persona;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PersonaRepository extends CrudRepository<Persona, Long> {
    // Spring Data automáticamente proporciona métodos CRUD básico
    
     List<Persona> findByNombre(String nombre);
}
