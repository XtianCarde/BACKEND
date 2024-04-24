package com.riwi.eventos.service.abstract_service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.riwi.eventos.entity.Evento;

public interface IEventoService {
    
    public Evento save(Evento objEvento);
    
    public List<Evento> listAll();

    public Evento findById(String id);

    public void delete(String id);

    public Evento update(Evento objEvento);

    public Page<Evento> findAllPaginable(int page, int size);

}
