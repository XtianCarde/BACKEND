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
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.EnrollmentRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.EnrollmentBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.IEnrollmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/enrollments")
@AllArgsConstructor
public class EnrollmentController {
    
    @Autowired
    private final IEnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<Page<EnrollmentBasicResp>> getAll(@RequestParam (defaultValue = "1") int page,
    @RequestParam (defaultValue = "10") int size){
        return ResponseEntity.ok(this.enrollmentService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentBasicResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.enrollmentService.get(id));
    }

    @PostMapping
     public ResponseEntity<EnrollmentBasicResp> insert(@Validated @RequestBody EnrollmentRequest request){
        return ResponseEntity.ok(this.enrollmentService.create(request));
     }

     @PutMapping(path = "/{id}")
     public ResponseEntity<EnrollmentBasicResp> update(@Validated @RequestBody EnrollmentRequest request,
        @PathVariable Long id){
        return ResponseEntity.ok(this.enrollmentService.update(request,id));
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        this.enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
     }
}
