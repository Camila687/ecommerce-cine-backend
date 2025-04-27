package com.cine.ecommerce_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cine.ecommerce_backend.model.Premiere;


@Repository
public interface PremiereRepository extends JpaRepository<Premiere, Long> {
    //
}
    

