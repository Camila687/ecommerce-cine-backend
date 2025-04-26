package com.cine.ecommerce_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ecommerce_backend.model.Candy;
import com.cine.ecommerce_backend.repository.CandyRepository;

@Service
public class CandyService {
    @Autowired
    private CandyRepository candyRepository;

    public List<Candy> obtenerCandys() {
        return candyRepository.listarCandy();
    }
}
