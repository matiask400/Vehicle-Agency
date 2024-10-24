package com.example.tpVehiculos.services;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.models.Interesados;
import com.example.tpVehiculos.models.Vehiculos;
import com.example.tpVehiculos.models.Empleados;
import com.example.tpVehiculos.repositories.PruebasDAO;
import com.example.tpVehiculos.repositories.InteresadoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;



@Service
public class PruebaService {

    @Autowired
    private PruebasDAO pruebasDAO;

    @Autowired
    private InteresadoDAO interesadoDAO;

    public Pruebas crearPrueba(Long idVehiculo, Long idInteresado, Long idEmpleado) {
        Interesados interesados = interesadoDAO.findById(idInteresado)
                .orElseThrow(() -> new RuntimeException("Interesado no encontrado"));

        if (interesados.isRestringido() || interesados.getFechaVencimientoLicencia().isBefore(LocalDate.now())) {
            throw new RuntimeException("Interesado con licencia vencida o restringido.");
        }

        Optional<Pruebas> pruebaActiva = pruebasDAO.findPruebaActivaByVehiculo(idVehiculo);
        if (pruebaActiva.isPresent()) {
            throw new RuntimeException("El vehículo ya está siendo probado.");
        }

        Pruebas nuevaPrueba = new Pruebas();
        nuevaPrueba.setVehiculo(new Vehiculos(idVehiculo));
        nuevaPrueba.setInteresado(interesados);
        nuevaPrueba.setEmpleado(new Empleados(idEmpleado));
        nuevaPrueba.setFechaHoraInicio(LocalDateTime.now());

        return pruebasDAO.save(nuevaPrueba);
    }
}

