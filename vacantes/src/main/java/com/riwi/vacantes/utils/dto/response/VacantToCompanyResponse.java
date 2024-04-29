package com.riwi.vacantes.utils.dto.response;

import com.riwi.vacantes.utils.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacantToCompanyResponse {
    private Long id;
    private String title;
    private String description;
    private Status status_vacant;
}
