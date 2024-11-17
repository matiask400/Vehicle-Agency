package com.example.notificaciones.services;

import com.example.notificaciones.controller.DTO.DTOPosicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void enviarNotificacion(DTOPosicion posicionDTO) {
        // Registrar notificación
        Notificacion notificacion = new Notificacion();
        notificacion.setVehiculoId(posicionDTO.getVehiculoId());
        notificacion.setMensaje("Vehículo fuera de límites o en zona peligrosa.");
        notificacionRepository.save(notificacion);

        // Restringir cliente
        Cliente cliente = clienteRepository.findByVehiculoId(posicionDTO.getVehiculoId());
        cliente.setRestringido(true);
        clienteRepository.save(cliente);
    }
}
