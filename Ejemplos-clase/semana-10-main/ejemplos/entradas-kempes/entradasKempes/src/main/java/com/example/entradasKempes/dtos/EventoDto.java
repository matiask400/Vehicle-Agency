/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entradasKempes.dtos;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class EventoDto {
    private int id_evento;
    private String nombre;
    private LocalDateTime fechaHora;
}
