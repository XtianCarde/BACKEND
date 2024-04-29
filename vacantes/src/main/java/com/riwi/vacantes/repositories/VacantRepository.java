package com.riwi.vacantes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.vacantes.entities.Vacant;

@Repository
public interface VacantRepository extends JpaRepository<Vacant, Long>{
    
}
