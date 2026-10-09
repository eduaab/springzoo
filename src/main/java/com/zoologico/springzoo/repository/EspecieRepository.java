package com.zoologico.springzoo.repository;

import com.zoologico.springzoo.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
