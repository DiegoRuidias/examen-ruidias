package com.ruidias.Examen01.repository;

import com.ruidias.Examen01.entity.Infracciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfraccionRepository extends JpaRepository<Infracciones, Integer> {
}
