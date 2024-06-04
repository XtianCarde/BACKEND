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
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.UserRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CoursesOfUser;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.SubmissionsOfUser;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UserBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.IUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    
    @Autowired
    private final IUserService userService;

    /* @GetMapping
    public ResponseEntity<Page<UserBasicResp>> getAll(@RequestParam(defaultValue = "1") int page,
     @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.userService.getAll(page - 1, size));
     } */

     @GetMapping(path = "/{id}/submissions")
     public ResponseEntity<SubmissionsOfUser> getSubmissionsOfUser(@PathVariable Long id){
         return ResponseEntity.ok(this.userService.getSubmissionsOfUser(id));
     }

     @GetMapping(path = "/{id}/courses")
     public ResponseEntity<CoursesOfUser> getCourseOfUser(@PathVariable Long id){
         return ResponseEntity.ok(this.userService.getCoursesOfUser(id));
     }

     @GetMapping(path = "/{id}")
     public ResponseEntity<UserBasicResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.get(id));
     }

     @PostMapping
     public ResponseEntity<UserBasicResp> insert(@Validated @RequestBody UserRequest request){
        return ResponseEntity.ok(this.userService.create(request));
     }

     @PutMapping(path = "/{id}")
     public ResponseEntity<UserBasicResp> update(@Validated @RequestBody UserRequest request,
        @PathVariable Long id){
        return ResponseEntity.ok(this.userService.update(request,id));
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
     }
}
