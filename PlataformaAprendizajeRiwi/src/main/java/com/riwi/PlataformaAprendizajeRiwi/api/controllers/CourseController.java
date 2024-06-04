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
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonsOfCourse;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.MessagesOfCourse;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UsersInCourse;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.ICourseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
public class CourseController {
    
    @Autowired
    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<Page<CourseBasicResp>> getAllCourses(@RequestParam (defaultValue = "1") int page,
    @RequestParam (defaultValue = "1") int size){
         return ResponseEntity.ok(this.courseService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}/lessons")
    public ResponseEntity<LessonsOfCourse> getAll(@PathVariable Long id){
        return ResponseEntity.ok(this.courseService.getlessonsByCourseId(id));
    }

    @GetMapping(path = "/{id}/messages")
    public ResponseEntity<MessagesOfCourse> getMessagesOfCourse(@PathVariable Long id){
        return ResponseEntity.ok(this.courseService.getMessagesOfCourse(id));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseBasicResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.courseService.get(id));
    }

    @GetMapping(path = "/{id}/users")
    public ResponseEntity<UsersInCourse> getUsersInCourse(@PathVariable Long id){
        return ResponseEntity.ok(this.courseService.getUsersInCourse(id));
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
