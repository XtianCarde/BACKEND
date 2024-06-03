package com.riwi.PlataformaAprendizajeRiwi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
