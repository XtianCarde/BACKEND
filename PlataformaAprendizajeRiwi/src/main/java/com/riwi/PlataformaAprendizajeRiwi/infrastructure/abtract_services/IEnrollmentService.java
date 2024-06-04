package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.EnrollmentRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.EnrollmentBasicResp;

public interface IEnrollmentService extends CrudService<EnrollmentRequest, EnrollmentBasicResp, Long> {

}
