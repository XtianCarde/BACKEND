package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.CourseRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CourseBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonsOfCourse;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.MessagesOfCourse;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UsersInCourse;

public interface ICourseService extends CrudService<CourseRequest, CourseBasicResp, Long> {
    LessonsOfCourse getlessonsByCourseId(Long courseId);
    UsersInCourse getUsersInCourse(Long id);
    MessagesOfCourse getMessagesOfCourse(Long id);
}
