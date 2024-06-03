package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;



import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.LessonRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonBasicResp;

public interface ILessonService extends CrudService<LessonRequest, LessonBasicResp, Long> {

}
