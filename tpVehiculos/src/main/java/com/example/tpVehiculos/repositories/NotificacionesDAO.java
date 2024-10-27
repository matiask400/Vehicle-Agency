package com.example.tpVehiculos.repositories;


import com.example.tpVehiculos.models.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// NotificationRepository.java
@Repository
public interface NotificacionesDAO extends JpaRepository<Notificaciones, Long> {}