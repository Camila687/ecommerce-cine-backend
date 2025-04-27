package com.cine.ecommerce_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ecommerce_backend.model.Candy;
import com.cine.ecommerce_backend.repository.CandyRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandyService {
    @Autowired
    private CandyRepository candyRepository;

    @Transactional(readOnly = true) 
    public List<Candy> obtenerCandys() {
        return candyRepository.findAll();
    }
}
