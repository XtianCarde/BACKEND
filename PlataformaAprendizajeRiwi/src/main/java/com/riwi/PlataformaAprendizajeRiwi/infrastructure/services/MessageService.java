package com.riwi.PlataformaAprendizajeRiwi.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.MessageRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.CourseBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.MessageBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UserBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Course;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Message;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.User;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.CourseRepository;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.MessageRepository;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.UserRepository;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.IMessageService;
import com.riwi.PlataformaAprendizajeRiwi.util.exceptions.BadRequestException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {
    
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CourseRepository courseRepository;

    @Override
    public MessageBasicResp create(MessageRequest rq) {
        Message entity = this.requestToEntity(rq);

        return this.entityToBasicResp(this.messageRepository.save(entity));
    }

    @Override
    public MessageBasicResp get(Long id) {
        return this.entityToBasicResp(this.find(id));
    }

    @Override
    public MessageBasicResp update(MessageRequest rq, Long id) {
        Message entity = this.find(id);
        entity = this.requestToEntity(rq);
        entity.setMessageId(id);

        return entityToBasicResp(this.messageRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.messageRepository.delete(this.find(id));
    }

    @Override
    public Page<MessageBasicResp> getAll(int page, int size) {
        
        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.messageRepository.findAll(pagination)
                    .map(this::entityToBasicResp);
    }

    private Message requestToEntity(MessageRequest request){

        return Message.builder()
                .userSender(this.findUser(request.getSenderId()))
                .userReceiver(this.findUser(request.getReceiverId()))
                .course(this.findCourse(request.getCourseId()))
                .messageContent(request.getMessageContent())
                .build();
                
    }

    private MessageBasicResp entityToBasicResp(Message entity){


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

    private Message find(Long id){
        return this.messageRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("El mensaje por el id suministrado no se encuentra"));
    }

    private User findUser(Long id){
        return this.userRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay usuarios con el id suministrado"));
    }

    private Course findCourse(Long id){
        return this.courseRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay cursos con el id suministrado"));
    }

}
