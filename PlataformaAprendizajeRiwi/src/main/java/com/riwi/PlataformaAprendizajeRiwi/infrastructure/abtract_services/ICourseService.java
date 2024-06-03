package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.CourseRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CourseBasicResp;

public interface ICourseService extends CrudService<CourseRequest, CourseBasicResp, Long> {
    
}
