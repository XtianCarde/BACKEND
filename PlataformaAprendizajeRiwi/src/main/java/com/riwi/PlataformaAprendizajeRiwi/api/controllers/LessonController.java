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
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.LessonRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.ILessonService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
public class LessonController {
    
    @Autowired
    private final ILessonService lessonService;

    @GetMapping
    public ResponseEntity<Page<LessonBasicResp>> getAll(@RequestParam(defaultValue = "1") int page,
     @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.lessonService.getAll(page - 1, size));
     }

     @GetMapping(path = "/{id}")
     public ResponseEntity<LessonBasicResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.lessonService.get(id));
     }

     @PostMapping
     public ResponseEntity<LessonBasicResp> insert(@Validated @RequestBody LessonRequest request){
        return ResponseEntity.ok(this.lessonService.create(request));
     }

     @PutMapping(path = "/{id}")
     public ResponseEntity<LessonBasicResp> update(@Validated @RequestBody LessonRequest request,
        @PathVariable Long id){
        return ResponseEntity.ok(this.lessonService.update(request,id));
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        this.lessonService.delete(id);
        return ResponseEntity.noContent().build();
     }
}
