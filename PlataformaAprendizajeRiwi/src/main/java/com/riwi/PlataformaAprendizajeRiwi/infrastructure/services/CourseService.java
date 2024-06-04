package com.riwi.PlataformaAprendizajeRiwi.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.CourseRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CourseBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonsOfCourse;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UserBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Course;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Lesson;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.User;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.CourseRepository;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.UserRepository;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.ICourseService;
import com.riwi.PlataformaAprendizajeRiwi.util.enums.Role;
import com.riwi.PlataformaAprendizajeRiwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService implements ICourseService {
    
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final UserRepository userRepository;
    
    @Override
    public CourseBasicResp create(CourseRequest rq) {
        Course entity = this.requestToEntity(rq);
        if (entity.getInstructor().getRole().equals(Role.INSTRUCTOR)) {
            return this.entityToResponse(this.courseRepository.save(entity));
        }else{
            throw new BadRequestException("No puedes acceder a la creación de cursos debido a tu rol");
        }
    }

    @Override
    public CourseBasicResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public CourseBasicResp update(CourseRequest rq, Long id) {
        Course entity = this.find(id);
        entity = requestToEntity(rq);
        entity.setCourseId(id);

        return entityToResponse(this.courseRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.courseRepository.delete(this.find(id));
    }

    @Override
    public Page<CourseBasicResp> getAll(int page, int size) {
        
        if(page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.courseRepository.findAll(pagination)
                            .map(this::entityToResponse);
    }
 
    public LessonsOfCourse getlessonsByCourseId(Long courseId){
        Course entity = this.find(courseId);
        List<LessonBasicResp> lessons = entity.getLessons()
                        .stream()
                        .map(this::entityToLessonBasciResp)
                        .collect(Collectors.toList());

        return LessonsOfCourse.builder()
                    .courseId(entity.getCourseId())
                    .courseName(entity.getCourseName())
                    .description(entity.getDescription())
                    .lessons(lessons)
                    .build();
    }

    private Course requestToEntity(CourseRequest request){
        return Course.builder()
                    .courseName(request.getCourseName())
                    .description(request.getDescription())
                    .instructor(this.findUser(request.getInstructorId()))
                    .build();
    }

    private CourseBasicResp entityToResponse(Course entity){

        UserBasicResp instructor = UserBasicResp.builder()
                        .userId(entity.getInstructor().getUserId())
                        .email(entity.getInstructor().getEmail())
                        .fullName(entity.getInstructor().getFullName())
                        .role(entity.getInstructor().getRole())
                        .build();

        return CourseBasicResp.builder()
                    .courseId(entity.getCourseId())
                    .courseName(entity.getCourseName())
                    .description(entity.getDescription())
                    .userInstructor(instructor)
                    .build();
    }

    private LessonBasicResp entityToLessonBasciResp(Lesson entity){
        
        UserBasicResp user = UserBasicResp.builder()
                        .userId(entity.getCourse().getInstructor().getUserId())
                        .email(entity.getCourse().getInstructor().getEmail())
                        .fullName(entity.getCourse().getInstructor().getFullName())
                        .role(entity.getCourse().getInstructor().getRole())
                        .build();

        CourseBasicResp course = CourseBasicResp.builder()
                        .courseId(entity.getCourse().getCourseId())
                        .courseName(entity.getCourse().getCourseName())
                        .description(entity.getCourse().getDescription())
                        .userInstructor(user)
                        .build();

        return LessonBasicResp.builder()
                        .lessonId(entity.getLessonId())
                        .lessonTitle(entity.getLessonTitle())
                        .content(entity.getContent())
                        .course(course)
                        .build();

    }

    private Course find(Long id){
        return this.courseRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay cursos por el id suministrado"));
    }

    private User findUser(Long id){
        return this.userRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay usuarios con el id suministrado"));
    }
}
