package com.riwi.eventos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.riwi.eventos.entity.Evento;
import com.riwi.eventos.repository.EventoRepository;
import com.riwi.eventos.service.abstract_service.IEventoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventoService implements IEventoService {

    @Autowired
    private final EventoRepository objEventoRepository;

    @Override
    public void delete(String id) {
        Evento objEvento = this.objEventoRepository.findById(id).orElseThrow();
        this.objEventoRepository.delete(objEvento);
    }

    @Override
    public Evento findById(String id) {
        return this.objEventoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Evento> listAll() {
        return this.objEventoRepository.findAll();
    }

    @Override
    public Page<Evento> findAllPaginable(int page, int size){
        if (page < 0) {
            page = 0;
        }

        Pageable objPage = PageRequest.of(page, size);
        return this.objEventoRepository.findAll(objPage);
    }

    @Override
    public Evento save(Evento objEvento) {
        return this.objEventoRepository.save(objEvento);
    }

    @Override
    public Evento update(Evento objEvento) {
        return this.objEventoRepository.save(objEvento);
    }
    
}
