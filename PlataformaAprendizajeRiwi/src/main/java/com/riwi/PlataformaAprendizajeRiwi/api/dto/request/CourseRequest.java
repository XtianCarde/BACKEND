package com.riwi.PlataformaAprendizajeRiwi.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotBlank(message = "El nombre del curso es requerido")
    @Size(min = 4,max = 30, message = "El nombre del curso debe ser entre 4 y 30 caracteres")
    private String courseName;
    @NotBlank(message = "La descripción del curso es requerido")
    @Size(max = 100, message = "La descripción del curso debe ser entre max de 100 caracteres")
    private String description;
    @NotNull(message = "El id del curso es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero")
    private Long instructorId;
}
