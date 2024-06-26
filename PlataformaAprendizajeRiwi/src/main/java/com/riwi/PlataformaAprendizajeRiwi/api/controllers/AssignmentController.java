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
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.AssignmentRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.AssignmentBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.SubmissionsOfAssignment;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.IAssignmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/assignments")
@AllArgsConstructor
public class AssignmentController {
    
    @Autowired
    private final IAssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<Page<AssignmentBasicResp>> getAll(@RequestParam (defaultValue = "1") int page,
    @RequestParam (defaultValue = "10") int size){
        return ResponseEntity.ok(this.assignmentService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AssignmentBasicResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.assignmentService.get(id));
    }

    @GetMapping(path = "/{id}/submissions")
    public ResponseEntity<SubmissionsOfAssignment> getSubmissionsOfAssignment(@PathVariable Long id){
         return ResponseEntity.ok(this.assignmentService.getSubmissionsOfAssignment(id));
    }

    @PostMapping
     public ResponseEntity<AssignmentBasicResp> insert(@Validated @RequestBody AssignmentRequest request){
        return ResponseEntity.ok(this.assignmentService.create(request));
     }

     @PutMapping(path = "/{id}")
     public ResponseEntity<AssignmentBasicResp> update(@Validated @RequestBody AssignmentRequest request,
        @PathVariable Long id){
        return ResponseEntity.ok(this.assignmentService.update(request,id));
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        this.assignmentService.delete(id);
        return ResponseEntity.noContent().build();
     }
}
