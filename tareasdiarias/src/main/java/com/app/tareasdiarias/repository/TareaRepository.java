package com.app.tareasdiarias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.tareasdiarias.entity.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea ,Long> {
    
}
