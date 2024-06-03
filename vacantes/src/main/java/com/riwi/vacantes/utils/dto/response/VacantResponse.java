package com.riwi.vacantes.utils.dto.response;

import com.riwi.vacantes.utils.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacantResponse {
    private Long id;
    private String description;
    private Status status_vacant;
    private String title;
    private CompanyToVacantResponse company;
}
