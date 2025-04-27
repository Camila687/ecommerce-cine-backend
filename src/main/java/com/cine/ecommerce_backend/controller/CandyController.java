package com.cine.ecommerce_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cine.ecommerce_backend.service.CandyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/candystore")
@Tag(name = "CandyStore", description = "API para consultar productos de la dulcería")
public class CandyController {

    @Autowired
    private CandyService candyService;

    @Operation(summary = "Listar dulces", description = "Devuelve una lista de productos de la dulcería.")
    @GetMapping
    public ResponseEntity<?> listarCandys() {
        try {
            return ResponseEntity.ok(candyService.obtenerCandys());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
