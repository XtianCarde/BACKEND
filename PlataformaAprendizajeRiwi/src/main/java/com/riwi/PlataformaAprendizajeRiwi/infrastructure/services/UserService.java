package com.riwi.PlataformaAprendizajeRiwi.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.UserRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UserBasicResp;
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

    private User find(Long id){
        return this.userRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay usuarios con el id suministrado"));
    }

}
