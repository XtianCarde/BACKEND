package com.riwi.primeraweb.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.riwi.primeraweb.entity.Coder;
import com.riwi.primeraweb.repository.CoderRepository;

@Service
public class CoderService {
    
    // @Autowired Registra la inyeccion de dependencias en SPRING BOOT 
    @Autowired
    private CoderRepository coderRepository;

    public List<Coder> findAll(){
        return this.coderRepository.findAll();
    }

    public Page<Coder> findAllPaginable(int page,int size){
        // Validar que la pag no sea menor que 0
        if (page < 0) {
            page = 0;
        }
        //Crear la paginación 
        Pageable objPage = PageRequest.of(page, size);
        return this.coderRepository.findAll(objPage);
    }

    public Coder create(Coder objCoder){
       return this.coderRepository.save(objCoder);
    }

    public void delete(Long id){
        this.coderRepository.deleteById(id);    
    }
    

    public Coder findById(Long id){
        return this.coderRepository.findById(id).orElse(null);
    }

    public Coder update(Long id, Coder objCoder){
        Coder objCoderDb = this.findById(id);
        if (objCoderDb == null) return null;
        objCoderDb = objCoder;
        return this.coderRepository.save(objCoderDb);
    }
}
