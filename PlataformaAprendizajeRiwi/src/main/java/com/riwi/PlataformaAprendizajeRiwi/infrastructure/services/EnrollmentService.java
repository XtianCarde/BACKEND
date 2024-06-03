package com.riwi.PlataformaAprendizajeRiwi.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.EnrollmentRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CourseBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.EnrollmentBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UserBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Course;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Enrollment;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.User;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.CourseRepository;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.EnrollmentRepository;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.UserRepository;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.IEnrollmentService;
import com.riwi.PlataformaAprendizajeRiwi.util.enums.Role;
import com.riwi.PlataformaAprendizajeRiwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnrollmentService implements IEnrollmentService {
    
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final EnrollmentRepository enrollmentRepository;
    
    @Override
    public EnrollmentBasicResp create(EnrollmentRequest rq) {
        Enrollment entity = this.requestToEntity(rq);
        if (entity.getStudent().getRole().equals(Role.STUDENT)) {
            return this.entityToBasicResp(this.enrollmentRepository.save(entity));
        }else{
            throw new BadRequestException("Tu rol no te permite crear una matricula");
        }
    }

    @Override
    public EnrollmentBasicResp get(Long id) {
        return this.entityToBasicResp(this.find(id));
    }

    @Override
    public EnrollmentBasicResp update(EnrollmentRequest rq, Long id) {
        Enrollment entity = this.find(id);
        entity = this.requestToEntity(rq);
        entity.setEnrollmentId(id);

        return this.entityToBasicResp(this.enrollmentRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.enrollmentRepository.delete(this.find(id));
    }

    @Override
    public Page<EnrollmentBasicResp> getAll(int page, int size) {
        
        if(page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.enrollmentRepository.findAll(pagination)
                        .map(this::entityToBasicResp);
    }

    private Enrollment requestToEntity(EnrollmentRequest request){ 

        return Enrollment.builder()
                    .student(this.findUser(request.getUserId()))
                    .course(this.findCourse(request.getCourseId()))
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
    
    private User findUser(Long id){
        return this.userRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay usuarios con el id suministrado"));
    }

    private Course findCourse(Long id){
        return this.courseRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay cursos por el id suministrado"));
    }

    private Enrollment find(Long id){
        return this.enrollmentRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay cursos por el id suministrado"));
    }
}
