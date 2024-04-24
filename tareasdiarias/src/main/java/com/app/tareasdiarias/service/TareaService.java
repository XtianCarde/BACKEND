package com.app.tareasdiarias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tareasdiarias.entity.Tarea;
import com.app.tareasdiarias.repository.TareaRepository;

@Service
public class TareaService {
    @Autowired
    private TareaRepository objTareaRepository;

    public List<Tarea> findAll(){
        return this.objTareaRepository.findAll();
    }

    public Tarea create(Tarea objTarea){
        return this.objTareaRepository.save(objTarea);
    }

    public void delete(Long id){
        this.objTareaRepository.deleteById(id);
    }

    public Tarea findById(Long id){
        return this.objTareaRepository.findById(id).orElse(null);
    }
    public Tarea update(Long id, Tarea objTarea){
        Tarea objTareaDb = this.findById(id);
        if (objTareaDb == null) return null;
        objTareaDb = objTarea;
        return this.objTareaRepository.save(objTareaDb);
    }
}
