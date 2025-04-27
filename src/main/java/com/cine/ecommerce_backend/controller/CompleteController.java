package com.cine.ecommerce_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cine.ecommerce_backend.model.PaymentRequest;
import com.cine.ecommerce_backend.service.PayUService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/complete")
public class CompleteController {

    @Autowired
    private PayUService payUService;

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> completarPago(@RequestBody PaymentRequest request) {
        
        return payUService.procesarPago(request)
            .map(respuesta -> {
                Map<String, Object> respuestaFinal = new HashMap<>();
                respuestaFinal.put("codigoRespuesta", "0");
                respuestaFinal.put("mensaje", "Transacción exitosa");
                respuestaFinal.put("datosPayU", respuesta);
                
                return ResponseEntity.ok(respuestaFinal);
            })
            .onErrorResume(e -> {
                e.printStackTrace();
                Map<String, Object> error = new HashMap<>();
                error.put("codigoRespuesta", "1");
                error.put("mensaje", "Error en la transacción: " + e.getMessage());
                
                return Mono.just(ResponseEntity.status(500).body(error));
            });
    }
}
