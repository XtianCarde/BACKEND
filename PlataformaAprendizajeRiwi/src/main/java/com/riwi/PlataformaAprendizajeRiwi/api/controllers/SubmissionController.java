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
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.SubmissionRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.SubmissionBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.ISubmissionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/submissions")
@AllArgsConstructor
public class SubmissionController {
    
    @Autowired
    private final ISubmissionService submissionService;

    @GetMapping
    public ResponseEntity<Page<SubmissionBasicResp>> getAll(@RequestParam(defaultValue = "1") int page,
     @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.submissionService.getAll(page - 1, size));
     }

     @GetMapping(path = "/{id}")
     public ResponseEntity<SubmissionBasicResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.submissionService.get(id));
     }

     @PostMapping
     public ResponseEntity<SubmissionBasicResp> insert(@Validated @RequestBody SubmissionRequest request){
        return ResponseEntity.ok(this.submissionService.create(request));
     }

     @PutMapping(path = "/{id}")
     public ResponseEntity<SubmissionBasicResp> update(@Validated @RequestBody SubmissionRequest request,
        @PathVariable Long id){
        return ResponseEntity.ok(this.submissionService.update(request,id));
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        this.submissionService.delete(id);
        return ResponseEntity.noContent().build();
     }
    
}
