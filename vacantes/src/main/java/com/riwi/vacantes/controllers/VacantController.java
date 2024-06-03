package com.riwi.vacantes.controllers;

import java.util.HashMap;
import java.util.Map;

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
import com.riwi.vacantes.services.interfaces.IVacantService;
import com.riwi.vacantes.utils.dto.errors.ErrorResponse;
import com.riwi.vacantes.utils.dto.request.VacantRequest;
import com.riwi.vacantes.utils.dto.response.VacantResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/vacant")
@AllArgsConstructor
@Tag(name = "Vacantes")
public class VacantController {
    
    @Autowired
    private final IVacantService vacantService;

    @Operation(summary = "Lista las vacantes por paginación", description = "Muestra la lista de vacantes paginadas")
    @GetMapping
    public ResponseEntity<Page<VacantResponse>> getAll(@RequestParam (defaultValue = "1")int page, @RequestParam (defaultValue = "5") int size){
        return ResponseEntity.ok(this.vacantService.getAll(page - 1, size));
    }

    @ApiResponse(
        responseCode = "400",
        description = "Cuando el id no es válido",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<VacantResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.vacantService.getById(id));
    }
    @PostMapping
    public ResponseEntity<VacantResponse> insert(@Validated @RequestBody VacantRequest vacant){
        return ResponseEntity.ok(this.vacantService.create(vacant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        response.put("message", "Se eliminó la vacante correctamente");
        this.vacantService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacantResponse> update(@PathVariable Long id,@Validated @RequestBody VacantRequest vacant){

        return ResponseEntity.ok(this.vacantService.update(id, vacant));
    }
}
