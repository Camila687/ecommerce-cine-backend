package com.cine.ecommerce_backend.service;

import com.cine.ecommerce_backend.model.PaymentRequest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayUService {

    private final WebClient webClient;

    public PayUService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://sandbox.api.payulatam.com").build();
    }

    public Mono<Map<String, Object>> procesarPago(PaymentRequest paymentRequest) {
        Map<String, Object> body = new HashMap<>();
        // Construir el body según PayU API (simplificado aquí)
        body.put("amount", "10000"); // valor de prueba
        body.put("currency", "PEN");
        body.put("paymentMethod", "VISA");
        body.put("card", Map.of(
            "number", paymentRequest.getNumeroTarjeta(),
            "expiration", paymentRequest.getFechaExpiracion(),
            "securityCode", paymentRequest.getCvv()
        ));

        return webClient.post()
        .uri("/payments-api/4.0/service.cgi")
        .bodyValue(body)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }
}
