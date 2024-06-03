package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.SubmissionRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.SubmissionBasicResp;

public interface ISubmissionService extends CrudService<SubmissionRequest, SubmissionBasicResp, Long> {
    
}
