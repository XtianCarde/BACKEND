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
public class AssignmentRequest {

    @NotBlank(message = "El titulo de la tarea es requerido")
    @Size(min = 4, max = 30, message = "El titulo debe contener entre 4 y 30 caracteres")
    private String assignmentTitle;
    @NotBlank(message = "La description de la tarea es requerida")
    @Size(min = 4, message = "La descripcion debe contener min 4 ")
    private String description;
    @NotNull(message = "El id de la leccion es requerido")
    @Min(value = 1, message = "El id debe ser valido mayor que cero")
    private Long lessonId;
}
