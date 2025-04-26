package com.cine.ecommerce_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.ecommerce_backend.model.Premiere;
import com.cine.ecommerce_backend.repository.PremiereRepository;

@Service
public class PremiereService {

    @Autowired
    private PremiereRepository premiereRepository;

    public List<Premiere> obtenerPremieres() {
        return premiereRepository.listarPremieres();
    }
    
}
