package com.riwi.vacantes.utils.dto.request;

import com.riwi.vacantes.utils.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacantRequest {
    
    private String description;
    private String title;
    private Status status_vacant;
    private String companyId;
}
