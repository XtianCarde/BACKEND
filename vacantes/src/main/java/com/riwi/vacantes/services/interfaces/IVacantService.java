package com.riwi.vacantes.services.interfaces;

import com.riwi.vacantes.utils.dto.request.VacantRequest;
import com.riwi.vacantes.utils.dto.response.VacantResponse;

public interface IVacantService extends CrudServices<VacantRequest,VacantResponse, Long>{
    VacantResponse getById(Long id);
}
