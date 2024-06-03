package com.riwi.PlataformaAprendizajeRiwi.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {
    
    @NotNull(message = "El id del usuario es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long userId;
    @NotNull(message = "El id del curso es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long courseId;
}
