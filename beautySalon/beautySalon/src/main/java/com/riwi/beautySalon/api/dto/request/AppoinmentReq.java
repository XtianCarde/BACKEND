package com.riwi.beautySalon.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
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
public class AppoinmentReq {
    @FutureOrPresent
    @NotBlank(message = "La fecha y hora de la cita es requerida")
    private LocalDateTime dateTime;
    @NotNull(message = "La duración es requerida")
    @Min( value = 5)
    @Max(value = 760)
    private Integer duration;
    private String comments;
    @NotNull(message = "El id del cliente es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long clientId;
    @NotNull(message = "El id del servicio es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long serviceId;
    @NotNull(message = "El id del empleado es requerido")
    @Min(value = 1, message = "El id debe ser mayor que cero")
    private Long employeeId;
}
