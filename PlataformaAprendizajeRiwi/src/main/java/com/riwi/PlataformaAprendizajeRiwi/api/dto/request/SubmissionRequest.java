package com.riwi.PlataformaAprendizajeRiwi.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequest {
    
    @NotBlank(message = "El contenido de la entrega es requerido")
    private String content;
    @NotNull(message = "El id del usuario es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long userId;
    @NotNull(message = "El grado de la tarea es requerido")
    private Double grade;
    @NotNull(message = "El id de la tarea es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long assignmentId;
}
