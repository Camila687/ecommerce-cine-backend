package com.cine.ecommerce_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.cine.ecommerce_backend.model.Candy;

@Repository
public interface CandyRepository extends JpaRepository<Candy, Long> {

    @Procedure(name = "Candy.listarCandy")
    List<Candy> listarCandy();
}
