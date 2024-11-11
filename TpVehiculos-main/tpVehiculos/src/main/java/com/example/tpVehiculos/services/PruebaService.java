package com.example.tpVehiculos.services;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.models.Interesados;
import com.example.tpVehiculos.models.Vehiculos;
import com.example.tpVehiculos.models.Empleados;
import com.example.tpVehiculos.repositories.PruebasDAO;
import com.example.tpVehiculos.repositories.InteresadoDAO;
import com.example.tpVehiculos.repositories.VehiculosDAO;
import com.example.tpVehiculos.repositories.EmpleadosDAO; // Asegúrate de tener EmpleadosDAO importado

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PruebaService {

    @Autowired
    private PruebasDAO pruebasDAO;

    @Autowired
    private InteresadoDAO interesadoDAO;

    @Autowired
    private VehiculosDAO vehiculosDAO;

    @Autowired
    private EmpleadosDAO empleadosDAO; // Repositorio de Empleados para verificar existencia

    @Transactional(readOnly = false)
    public Pruebas crearPrueba(Long idVehiculo, Long idInteresado, Long idEmpleado) {
        // Verifica si el interesado existe en la base de datos
        Interesados interesados = interesadoDAO.findById(idInteresado)
                .orElseThrow(() -> new RuntimeException("Interesado no encontrado"));

        // Verifica si el interesado tiene licencia válida y no está restringido
        if (interesados.isRestringido() || interesados.getFechaVencimientoLicencia().isBefore(LocalDate.now())) {
            throw new RuntimeException("Interesado con licencia vencida o restringido.");
        }

        // Verifica si el vehículo existe en la base de datos
        Vehiculos vehiculo = vehiculosDAO.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        // Verifica si el empleado existe en la base de datos
        Empleados empleado = empleadosDAO.findById(idEmpleado)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        // Verifica si el vehículo ya está siendo probado
        Optional<Pruebas> pruebaActiva = pruebasDAO.findPruebaActivaByVehiculo(idVehiculo);
        if (pruebaActiva.isPresent()) {
            throw new RuntimeException("El vehículo ya está siendo probado.");
        }

        // Crea la nueva prueba con los datos validados
        Pruebas nuevaPrueba = new Pruebas();
        nuevaPrueba.setVehiculo(vehiculo); // Asigna el objeto Vehiculos verificado
        nuevaPrueba.setInteresado(interesados);
        nuevaPrueba.setEmpleado(empleado); // Asigna el objeto Empleados verificado
        nuevaPrueba.setFechaHoraInicio(LocalDateTime.now());

        return pruebasDAO.save(nuevaPrueba);
    }

    public List<Pruebas> listarPruebasEnCurso(LocalDateTime fechaHora) {
        return pruebasDAO.findPruebasEnCurso(fechaHora);
    }
}
