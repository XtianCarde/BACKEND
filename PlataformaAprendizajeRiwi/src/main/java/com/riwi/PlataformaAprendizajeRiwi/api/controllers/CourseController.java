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
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.CourseRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CourseBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.ICourseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
public class CourseController {
    
    @Autowired
    private final ICourseService courseService;

    @GetMapping(path = "/{id}/lessons")
    public ResponseEntity<Page<CourseBasicResp>> getAll(@RequestParam (defaultValue = "1") int page,
    @RequestParam (defaultValue = "10") int size, @PathVariable Long id){
        return ResponseEntity.ok(this.courseService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseBasicResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.courseService.get(id));
    }

    @PostMapping
     public ResponseEntity<CourseBasicResp> insert(@Validated @RequestBody CourseRequest request){
        return ResponseEntity.ok(this.courseService.create(request));
     }

     @PutMapping(path = "/{id}")
     public ResponseEntity<CourseBasicResp> update(@Validated @RequestBody CourseRequest request,
        @PathVariable Long id){
        return ResponseEntity.ok(this.courseService.update(request,id));
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        this.courseService.delete(id);
        return ResponseEntity.noContent().build();
     }
}
