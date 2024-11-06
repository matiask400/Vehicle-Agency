package com.example.tpVehiculos.controller;

import com.example.tpVehiculos.services.NotificacionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos/promociones")
@RequiredArgsConstructor
public class PromocionController {

    private final NotificacionService notificationService;

    @PostMapping("/enviar")
    public void enviarPromocion(@RequestBody PromocionRequest request) {
        notificationService.enviarPromocion(request.getMensaje(), request.getTelefonos());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class PromocionRequest {
        private String mensaje;
        private List<String> telefonos;
    }
}
