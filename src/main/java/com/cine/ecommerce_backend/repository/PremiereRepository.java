package com.cine.ecommerce_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.cine.ecommerce_backend.model.Premiere;


@Repository
public interface PremiereRepository extends JpaRepository<Premiere, Long> {

    @Procedure(name = "Premiere.listarPremieres")
    List<Premiere> listarPremieres();
}
    

