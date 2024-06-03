package com.riwi.vacantes.utils.dto.request;

import com.riwi.vacantes.utils.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacantRequest {
    @NotBlank(message = "La descripción es requerida")
    private String description;
    @NotBlank(message = "El titulo es requerido")
    private String title;
    private Status status_vacant;
    @NotBlank(message = "El id de la compañia es requerido")
    @Size(max = 36,min = 0,message = "El id sobrepasa la cantidad los caracteres permitidos")
    private String companyId;
}
