package com.riwi.beautySalon.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.riwi.beautySalon.domain.entities.Appoinment;

@Repository
public interface AppoinmentRepository extends JpaRepository<Appoinment, Long>{
    
    @Query(value = "select a from appoinment a join fetch a.client c where c.id = :idClient")
    Optional<Appoinment> findByClientId(Long idClient);
}
