package com.cine.ecommerce_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cine.ecommerce_backend.model.Premiere;
import com.cine.ecommerce_backend.service.PremiereService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/premieres")
@Tag(name = "Premieres", description = "API para consultar premieres de cine")
public class PremiereController {

    @Autowired
    private PremiereService premiereService;

    @Operation(summary = "Listar premieres", description = "Devuelve una lista de estrenos disponibles.")
    @GetMapping
    public ResponseEntity<List<Premiere>> listarPremieres() {
        return ResponseEntity.ok(premiereService.obtenerPremieres());
    }
}
