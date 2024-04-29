package com.riwi.vacantes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.vacantes.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    
}
