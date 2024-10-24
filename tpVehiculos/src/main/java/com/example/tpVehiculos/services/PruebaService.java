package com.example.tpVehiculos.services;

import com.example.tpVehiculos.repositories.ClienteRepository;
import com.example.tpVehiculos.repositories.PruebaRepository;
import com.example.tpVehiculos.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PruebaService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private PruebaRepository pruebaRepository;

    public String crearPrueba(Long clienteId, String patenteVehiculo) {
        // Verificar que el cliente exista
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        // Verificar si está restringido
        if (cliente.isRestringido()) {
            return "El cliente está restringido para probar vehículos.";
        }

        // Verificar licencia
        if (cliente.getLicencia() == null || cliente.getLicencia().getFechaExpiracion().isBefore(LocalDate.now())) {
            return "Licencia vencida o no registrada.";
        }

        // Verificar que el vehículo no esté en prueba
        Vehiculo vehiculo = vehiculoRepository.findById(patenteVehiculo)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));

        if (vehiculo.isEnPrueba()) {
            return "El vehículo ya está en prueba.";
        }

        // Verificar que no haya otra prueba en curso para ese vehículo
        if (pruebaRepository.existsByVehiculoAndFechaFinIsNull(vehiculo)) {
            return "El vehículo ya está en prueba.";
        }

        // Crear la nueva prueba
        Prueba nuevaPrueba = new Prueba();
        nuevaPrueba.setCliente(cliente);
        nuevaPrueba.setVehiculo(vehiculo);
        nuevaPrueba.setFechaInicio(LocalDateTime.now());

        vehiculo.setEnPrueba(true); // Marcar el vehículo como en prueba

        pruebaRepository.save(nuevaPrueba);
        vehiculoRepository.save(vehiculo);

        return "Prueba creada exitosamente.";
    }
}
