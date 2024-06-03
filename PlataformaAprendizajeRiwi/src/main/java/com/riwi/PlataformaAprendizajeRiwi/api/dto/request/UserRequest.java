package com.riwi.PlataformaAprendizajeRiwi.api.dto.request;

import com.riwi.PlataformaAprendizajeRiwi.util.enums.Role;
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
public class UserRequest {

    @NotBlank(message = "El username es requerido")
    @Size(min = 2,max = 20,message = "El username debe tener entre 2 y 20 caracteres")
    private String username;
    @NotBlank(message = "El password es requerido")
    @Size(min = 8,message = "El password debe tener entre 8 minimo caracteres")
    private String password;
    @NotBlank(message = "El email es requerido")
    @Size(min = 10,max = 40,message = "El email debe tener entre 10 y 40 caracteres")
    private String email;
    @NotBlank(message = "El nombre completo es requerido")
    @Size(min = 10,max = 60,message = "El username debe tener entre 10 y 60 caracteres")
    private String fullName;
    @NotNull(message = "El role es requerido")
    private Role role;
}
