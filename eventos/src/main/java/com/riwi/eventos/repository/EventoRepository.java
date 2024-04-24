package com.riwi.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.eventos.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, String> {
    
}
