package com.cine.ecommerce_backend.service;

import com.cine.ecommerce_backend.model.PaymentRequest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayUService {

    private final WebClient webClient;
    
    // API Key y Merchant ID de pruebas para PayU (campos de prueba)
    private static final String API_KEY = "4Vj8eK4rloUd272L48hsrarnUA";
    private static final String MERCHANT_ID = "508029";
    private static final String ACCOUNT_ID = "512321";

    public PayUService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("https://sandbox.api.payulatam.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    public Mono<Map<String, Object>> procesarPago(PaymentRequest paymentRequest) {
        Map<String, Object> paymentData = new HashMap<>();
        
        // Inf de la transacción
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("type", "AUTHORIZATION_AND_CAPTURE");
        transaction.put("paymentMethod", "VISA");
        transaction.put("paymentCountry", "PE");
        transaction.put("deviceSessionId", "vghs6tvkcle931686k1900o6e1");
        transaction.put("ipAddress", "127.0.0.1");
        transaction.put("userAgent", "Mozilla/5.0");
        transaction.put("cookie", "cookie_value");
        
        // Inf de la orden
        Map<String, Object> order = new HashMap<>();
        order.put("accountId", ACCOUNT_ID);
        order.put("referenceCode", "payment_test_" + System.currentTimeMillis());
        order.put("description", "Payment test");
        order.put("language", "es");
        
        // Inf de valores
        Map<String, Object> additionalValues = new HashMap<>();
        Map<String, Object> txValue = new HashMap<>();
        txValue.put("value", 100);
        txValue.put("currency", "PEN");
        additionalValues.put("TX_VALUE", txValue);
        order.put("additionalValues", additionalValues);
        
        // Inf del comprador
        Map<String, Object> buyer = new HashMap<>();
        buyer.put("fullName", paymentRequest.getNombre());
        buyer.put("emailAddress", paymentRequest.getCorreo());
        buyer.put("contactPhone", "7563126");
        buyer.put("dniNumber", paymentRequest.getNumeroDocumento());
        order.put("buyer", buyer);
        
        // Inf de la tarjeta de crédito
        Map<String, Object> creditCard = new HashMap<>();
        creditCard.put("number", paymentRequest.getNumeroTarjeta());
        creditCard.put("securityCode", paymentRequest.getCvv());
        
        // Parseo fecha de expiración (esperado: YYYY/MM)
        String[] expParts = paymentRequest.getFechaExpiracion().split("/");
        creditCard.put("expirationDate", expParts[1] + "/" + expParts[0]);
        
        creditCard.put("name", paymentRequest.getNombre());
        transaction.put("creditCard", creditCard);
        
        // Estructura request
        paymentData.put("language", "es");
        paymentData.put("command", "SUBMIT_TRANSACTION");
        paymentData.put("merchant", Map.of(
            "apiKey", API_KEY,
            "apiLogin", "pRRXKOl8ikMmt9u"
        ));
        paymentData.put("transaction", transaction);
        paymentData.put("test", true);
        
        System.out.println("Enviando a PayU: " + paymentData);

        return webClient.post()
            .uri("/payments-api/4.0/service.cgi")
            .bodyValue(paymentData)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
            .onErrorResume(WebClientResponseException.class, e -> {
                System.err.println("Error en PayU: " + e.getResponseBodyAsString());
                return Mono.error(new RuntimeException("Error al procesar el pago: " + e.getMessage()));
            });
    }
}
