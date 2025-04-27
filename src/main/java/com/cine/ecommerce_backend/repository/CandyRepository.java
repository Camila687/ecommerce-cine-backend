package com.cine.ecommerce_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cine.ecommerce_backend.model.Candy;

@Repository
public interface CandyRepository extends JpaRepository<Candy, Long> {
    List<Candy> findAll();
}