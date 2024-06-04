package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.CourseRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CourseBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonsOfCourse;

public interface ICourseService extends CrudService<CourseRequest, CourseBasicResp, Long> {
    LessonsOfCourse getlessonsByCourseId(Long courseId);
}
