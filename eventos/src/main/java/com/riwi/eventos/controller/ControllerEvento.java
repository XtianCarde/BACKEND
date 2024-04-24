package com.riwi.eventos.controller;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.eventos.entity.Evento;
import com.riwi.eventos.service.abstract_service.IEventoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/events")
@AllArgsConstructor
public class ControllerEvento {
    
    @Autowired
    public final IEventoService objIEventoService;

    @GetMapping
    public ResponseEntity<Page<Evento>> listAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "2") int size){
        return ResponseEntity.ok(this.objIEventoService.findAllPaginable(page -1,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> findById(@PathVariable String id){
        return ResponseEntity.ok(this.objIEventoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody Evento objEvento){
        if (objEvento.getFecha().isBefore(LocalDate.now()) || objEvento.getCapacidad() < 0) {
            return null;
        }
        return ResponseEntity.ok(this.objIEventoService.save(objEvento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(@RequestBody Evento objEvento, @PathVariable String id){
        objEvento.setId(id);
        if (objEvento.getFecha().isBefore(LocalDate.now()) || objEvento.getCapacidad() < 0) {
            return null;
        }
        return ResponseEntity.ok(this.objIEventoService.update(objEvento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.objIEventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
