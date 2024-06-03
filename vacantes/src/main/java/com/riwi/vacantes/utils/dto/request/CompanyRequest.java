package com.riwi.vacantes.utils.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
    @NotBlank(message = "El nombre de la compañía es requerido")
    @Size(min = 0, max = 40, message = "El nombre supera la cantidad de caracteres permitidos")
    private String name;
    @NotBlank(message = "La locación de la compañía es requerido")
    private String location;
    @Size(min = 0, max = 14, message = "El contacto supera la cantidad de caracteres permitidos")
    @NotBlank(message = "El contacto de la compañía es requerido")
    private String contact;
}
