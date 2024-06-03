package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.AssignmentRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.AssignmentBasicResp;

public interface IAssignmentService extends CrudService<AssignmentRequest, AssignmentBasicResp, Long> {
    
}
