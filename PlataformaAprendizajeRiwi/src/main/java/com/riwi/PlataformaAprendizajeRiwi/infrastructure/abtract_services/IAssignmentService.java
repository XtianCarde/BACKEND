package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.AssignmentRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.AssignmentBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.SubmissionsOfAssignment;

public interface IAssignmentService extends CrudService<AssignmentRequest, AssignmentBasicResp, Long> {
    SubmissionsOfAssignment getSubmissionsOfAssignment(Long id);
}
