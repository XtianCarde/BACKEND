package com.riwi.PlataformaAprendizajeRiwi.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.LessonRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.AssignmentBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.AssignmentsOfLesson;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Assignment;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Course;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Lesson;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.CourseRepository;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.LessonRepository;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.ILessonService;
import com.riwi.PlataformaAprendizajeRiwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {
    
    @Autowired
    private final LessonRepository lessonRepository;
    @Autowired
    private final CourseRepository courseRepository;
    
    @Override
    public LessonBasicResp create(LessonRequest rq) {
        Lesson entity = this.requestToEntity(rq);

        return this.entityToBasciResp(this.lessonRepository.save(entity));
    }

    @Override
    public LessonBasicResp get(Long id) {
        return this.entityToBasciResp(this.find(id));
    }

    @Override
    public LessonBasicResp update(LessonRequest rq, Long id) {
        Lesson entity = this.find(id);
        entity = this.requestToEntity(rq);
        entity.setLessonId(id);

        return this.entityToBasciResp(this.lessonRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.lessonRepository.delete(this.find(id));
    }

    @Override
    public Page<LessonBasicResp> getAll(int page, int size) {
        if(page < 0) page = 0;
        
        PageRequest pagination = PageRequest.of(page, size);
        
        return this.lessonRepository.findAll(pagination)
        .map(this::entityToBasciResp);
    }

    @Override
    public AssignmentsOfLesson getAssignmentsOfLesson(Long id) {
        Lesson entity = this.find(id);

        List<AssignmentBasicResp> assignments = entity.getAssignments()
                        .stream()
                        .map(this::assignmentToResponse)
                        .collect(Collectors.toList());
        
        return AssignmentsOfLesson.builder()
                    .lessonId(entity.getLessonId())
                    .lessonTitle(entity.getLessonTitle())
                    .content(entity.getContent())
                    .assignments(assignments)
                    .build();

    }
    
    private Lesson requestToEntity(LessonRequest request){
        return Lesson.builder()
                    .lessonTitle(request.getLessonTitle())
                    .content(request.getContent())
                    .course(this.findCourse(request.getCourseId()))
                    .build();
    }

    private LessonBasicResp entityToBasciResp(Lesson entity){

        return LessonBasicResp.builder()
                        .lessonId(entity.getLessonId())
                        .lessonTitle(entity.getLessonTitle())
                        .content(entity.getContent())
                        .build();

    }

    private AssignmentBasicResp assignmentToResponse(Assignment entity){
        return AssignmentBasicResp.builder()
                        .assignmentId(entity.getAssignmentId())
                        .assignmentTitle(entity.getAssignmentTitle())
                        .description(entity.getDescription())
                        .dueDate(entity.getDueDate())
                        .build();
    }


    private Lesson find(Long id){
        return this.lessonRepository.findById(id)
                        .orElseThrow(() -> new BadRequestException("No hay lecciones por el id suministrado"));
    }

    private Course findCourse(Long id){
        return this.courseRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay cursos por el id suministrado"));
    }

}
