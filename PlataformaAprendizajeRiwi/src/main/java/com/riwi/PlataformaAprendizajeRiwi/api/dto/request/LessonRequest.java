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
public class LessonRequest {
    
    @NotBlank(message = "El titulo es requerido")
    @Size(min = 2,max = 40, message = "El titulo debe contener entre 2 y 40 caracteres")
    private String lessonTitle;
    @NotBlank(message = "El contenido es requerido")
    @Size(min = 5, message = "El contenido debe contener min 5 caracteres")
    private String content;
    @NotNull(message = "El id del curso es requerido")
    @Min(value = 1, message = "Ingresa un id valido mayor a cero")
    private Long courseId;
}
