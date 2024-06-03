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
public class MessageRequest {
    
    @NotNull(message = "El id del usuario enviador es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long senderId;
    @NotNull(message = "El id del usuario recibidor es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long receiverId;
    @NotNull(message = "El id del curso es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long courseId;
    @NotBlank(message = "El mensaje es requerido")
    @Size(min = 4, message = "El mensaje debe ser mayor de 4 caracteres" )
    private String messageContent;
}
