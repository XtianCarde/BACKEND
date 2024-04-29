package com.riwi.vacantes.services.interfaces;

import com.riwi.vacantes.utils.dto.request.CompanyRequest;
import com.riwi.vacantes.utils.dto.response.CompanyResponse;

public interface ICompanyService extends CrudServices<CompanyRequest, CompanyResponse, String>{
    CompanyResponse getById(String id);
}
