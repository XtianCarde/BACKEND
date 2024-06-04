package com.riwi.PlataformaAprendizajeRiwi.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.UserRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CourseBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CoursesOfUser;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UserBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Course;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.User;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.UserRepository;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.IUserService;
import com.riwi.PlataformaAprendizajeRiwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserBasicResp create(UserRequest rq) {
        User entity = this.requestToEntity(rq);
        return this.entityToBasicResp(this.userRepository.save(entity));
    }

    @Override
    public UserBasicResp get(Long id) {
        return this.entityToBasicResp(this.find(id));
    }

    @Override
    public UserBasicResp update(UserRequest rq, Long id) {
        User entity = this.find(id);
        entity = this.requestToEntity(rq);
        entity.setUserId(id);

        return this.entityToBasicResp(this.userRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.userRepository.delete(this.find(id));
    }

    @Override
    public Page<UserBasicResp> getAll(int page, int size) {
        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        
        return this.userRepository.findAll(pagination)
        .map(this::entityToBasicResp);
    }
    
    @Override
    public CoursesOfUser getCoursesOfUser(Long id) {
        User user = this.find(id);

        List<CourseBasicResp> courses = user.getCourses()
                    .stream()
                    .map(this::CourseToResponse)
                    .collect(Collectors.toList());

        return CoursesOfUser.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .courses(courses)
                .build();
    }

    private User requestToEntity(UserRequest userRequest){

        return User.builder()
        .username(userRequest.getUsername())
        .password(userRequest.getPassword())
        .email(userRequest.getEmail())
        .fullName(userRequest.getFullName())
        .role(userRequest.getRole())
        .build();
    }

    private UserBasicResp entityToBasicResp(User user){
        return UserBasicResp.builder()
        .userId(user.getUserId())
        .email(user.getEmail())
        .fullName(user.getFullName())
        .role(user.getRole())
        .build();
    }

    private CourseBasicResp CourseToResponse(Course entity){

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

    private User find(Long id){
        return this.userRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay usuarios con el id suministrado"));
    }


}
