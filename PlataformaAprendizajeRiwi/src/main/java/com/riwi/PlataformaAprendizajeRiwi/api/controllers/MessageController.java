package com.riwi.PlataformaAprendizajeRiwi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.MessageRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.MessageBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.IMessageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/messages")
@AllArgsConstructor
public class MessageController {
    
    @Autowired
    private final IMessageService messageService;
    
    @GetMapping
    public ResponseEntity<Page<MessageBasicResp>> getAll(@RequestParam(defaultValue = "1") int page,
     @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.messageService.getAll(page - 1, size));
     }

     @GetMapping(path = "/{id}")
     public ResponseEntity<MessageBasicResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.messageService.get(id));
     }

     @PostMapping
     public ResponseEntity<MessageBasicResp> insert(@Validated @RequestBody MessageRequest request){
        return ResponseEntity.ok(this.messageService.create(request));
     }

     @PutMapping(path = "/{id}")
     public ResponseEntity<MessageBasicResp> update(@Validated @RequestBody MessageRequest request,
        @PathVariable Long id){
        return ResponseEntity.ok(this.messageService.update(request,id));
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        this.messageService.delete(id);
        return ResponseEntity.noContent().build();
     }
}
