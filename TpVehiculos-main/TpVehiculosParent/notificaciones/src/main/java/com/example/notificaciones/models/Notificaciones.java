package com.example.notificaciones.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime timestamp;

    public Notificaciones(Object o, String mensaje, LocalDateTime now, String telefono) {
    }

    public Notificaciones() {

    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

