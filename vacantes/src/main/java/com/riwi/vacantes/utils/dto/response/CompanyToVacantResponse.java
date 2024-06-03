package com.riwi.vacantes.utils.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyToVacantResponse {
    private String id;
    private String contact;
    private String location;
    private String name;
}
