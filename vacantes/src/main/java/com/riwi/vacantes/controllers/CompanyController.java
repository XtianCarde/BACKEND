package com.riwi.vacantes.controllers;


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
import com.riwi.vacantes.services.interfaces.ICompanyService;
import com.riwi.vacantes.utils.dto.request.CompanyRequest;
import com.riwi.vacantes.utils.dto.response.CompanyResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {
    
    private final ICompanyService objCompanyService;

    @GetMapping
    public ResponseEntity<Page<CompanyResponse>> get(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "2") int size){

        return ResponseEntity.ok(this.objCompanyService.getAll(page -1, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> findById(@PathVariable String id){
        return ResponseEntity.ok(this.objCompanyService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> insert(@Validated @RequestBody CompanyRequest CR){
        return ResponseEntity.ok(this.objCompanyService.create(CR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.objCompanyService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(@PathVariable String id,@Validated @RequestBody CompanyRequest company){
        return ResponseEntity.ok(this.objCompanyService.update(id, company));
    }
}
