package com.riwi.vacantes.utils.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {
    
    private String id;
    private String contact;
    private String location;
    private String name;
    private List<VacantToCompanyResponse> vacantes;
}
