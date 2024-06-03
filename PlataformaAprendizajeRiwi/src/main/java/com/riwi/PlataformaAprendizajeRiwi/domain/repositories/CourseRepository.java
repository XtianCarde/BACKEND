package com.riwi.PlataformaAprendizajeRiwi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
