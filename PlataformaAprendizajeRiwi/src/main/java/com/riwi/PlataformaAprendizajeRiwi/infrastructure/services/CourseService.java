package com.riwi.PlataformaAprendizajeRiwi.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.CourseRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CourseBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.EnrollmentBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.LessonsOfCourse;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.MessageBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.MessagesOfCourse;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UserBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UsersInCourse;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Course;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Enrollment;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Lesson;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Message;
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

        UserBasicResp instructor = UserBasicResp.builder()
                        .userId(entity.getInstructor().getUserId())
                        .email(entity.getInstructor().getEmail())
                        .fullName(entity.getInstructor().getFullName())
                        .role(entity.getInstructor().getRole())
                        .build();

        return LessonsOfCourse.builder()
                    .courseId(entity.getCourseId())
                    .courseName(entity.getCourseName())
                    .description(entity.getDescription())
                    .userInstructor(instructor)
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
                    
    @Override
    public UsersInCourse getUsersInCourse(Long id) {
        Course entity = this.find(id);

        List<EnrollmentBasicResp> users = entity.getStudent()
                    .stream()
                    .map(this::entityToBasicResp)
                    .collect(Collectors.toList());

        return UsersInCourse.builder()
                    .courseId(entity.getCourseId())
                    .courseName(entity.getCourseName())
                    .description(entity.getDescription())
                    .users(users)
                    .build();
                    
    }

    private LessonBasicResp entityToLessonBasciResp(Lesson entity){
        return LessonBasicResp.builder()
                        .lessonId(entity.getLessonId())
                        .lessonTitle(entity.getLessonTitle())
                        .content(entity.getContent())
                        .build();
    }

    private EnrollmentBasicResp entityToBasicResp(Enrollment entity){
        
        UserBasicResp student = UserBasicResp.builder()
                        .userId(entity.getStudent().getUserId())
                        .email(entity.getStudent().getEmail())
                        .fullName(entity.getStudent().getFullName())
                        .role(entity.getStudent().getRole())
                        .build();

                        
                        UserBasicResp instructor = UserBasicResp.builder()
                        .userId(entity.getCourse().getInstructor().getUserId())
                        .email(entity.getCourse().getInstructor().getEmail())
                        .fullName(entity.getCourse().getInstructor().getFullName())
                        .role(entity.getCourse().getInstructor().getRole())
                        .build();
                        
                        CourseBasicResp course = CourseBasicResp.builder()
                        .courseId(entity.getCourse().getCourseId())
                        .courseName(entity.getCourse().getCourseName())
                        .description(entity.getCourse().getDescription())
                        .userInstructor(instructor)
                        .build();
                        
                        
                        return EnrollmentBasicResp.builder()
                        .enrollmentId(entity.getEnrollmentId())
                        .student(student)
                        .course(course)
                        .build();
                    }
                    
                    
                    
    @Override
    public MessagesOfCourse getMessagesOfCourse(Long id) {
        Course entity = this.find(id);

        List<MessageBasicResp> messages = entity.getMessages()
                            .stream()
                            .map(this::messageToBasicResp)
                            .collect(Collectors.toList());

        return MessagesOfCourse.builder()
                    .courseId(entity.getCourseId())
                    .courseName(entity.getCourseName())
                    .description(entity.getDescription())
                    .messages(messages)
                    .build();
    }

    private MessageBasicResp messageToBasicResp(Message entity){


        UserBasicResp userSender =  UserBasicResp.builder()
                    .email( entity.getUserSender().getEmail())
                    .fullName(entity.getUserSender().getFullName())
                    .build();

        UserBasicResp userReceiver =  UserBasicResp.builder()
                    .email( entity.getUserSender().getEmail())
                    .fullName(entity.getUserSender().getFullName())
                    .build();

        CourseBasicResp course = CourseBasicResp.builder()
                    .courseName(entity.getCourse().getCourseName())
                    .description(entity.getCourse().getDescription())
                    .build();

        return MessageBasicResp.builder()
                    .messageId(entity.getMessageId())
                    .userSender(userSender)
                    .userReceiver(userReceiver)
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
