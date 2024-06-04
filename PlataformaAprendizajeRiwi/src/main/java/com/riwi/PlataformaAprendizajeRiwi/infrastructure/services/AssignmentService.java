package com.riwi.PlataformaAprendizajeRiwi.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.AssignmentRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.AssignmentBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.SubmissionBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.SubmissionsOfAssignment;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Assignment;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Submission;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.AssignmentRepository;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.IAssignmentService;
import com.riwi.PlataformaAprendizajeRiwi.util.exceptions.BadRequestException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssignmentService implements IAssignmentService{
    
    @Autowired
    private final AssignmentRepository assignmentRepository;

    @Override
    public AssignmentBasicResp create(AssignmentRequest rq) {
        Assignment entity = this.requestToEntity(rq);
        return this.entityToResponse(this.assignmentRepository.save(entity));
    }

    @Override
    public AssignmentBasicResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public AssignmentBasicResp update(AssignmentRequest rq, Long id) {
        Assignment entity = this.find(id);
        entity = this.requestToEntity(rq);
        entity.setAssignmentId(id);

        return this.entityToResponse(this.assignmentRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.assignmentRepository.delete(this.find(id));
    }

    @Override
    public Page<AssignmentBasicResp> getAll(int page, int size) {
        if (page < 0) page = 0;
        
        PageRequest pagination = PageRequest.of(page, size);

        return this.assignmentRepository.findAll(pagination)
                    .map(this::entityToResponse);
    }

    @Override
    public SubmissionsOfAssignment getSubmissionsOfAssignment(Long id) {
        Assignment entity = this.find(id);

        List<SubmissionBasicResp> submissions = entity.getSubmissions()
                        .stream()
                        .map(this::submissionToResponse)
                        .collect(Collectors.toList());
        
        return SubmissionsOfAssignment.builder()
                    .assignmentId(entity.getAssignmentId())
                    .assignmentTitle(entity.getAssignmentTitle())
                    .description(entity.getDescription())
                    .dueDate(entity.getDueDate())
                    .submissions(submissions)
                    .build();
    }

    private Assignment requestToEntity(AssignmentRequest request){
        return Assignment.builder()
            .assignmentTitle(request.getAssignmentTitle())
            .description(request.getDescription())
            .build();
    }

    private AssignmentBasicResp entityToResponse(Assignment entity){
        return AssignmentBasicResp.builder()
                        .assignmentId(entity.getAssignmentId())
                        .assignmentTitle(entity.getAssignmentTitle())
                        .description(entity.getDescription())
                        .dueDate(entity.getDueDate())
                        .build();
    }

    private SubmissionBasicResp submissionToResponse(Submission entity){
        return SubmissionBasicResp.builder()
                    .submissionId(entity.getSubmissionId())
                    .content(entity.getContent())
                    .grade(entity.getGrade())
                    .submissionDate(entity.getSubmissionDate())
                    .build();
    }

    private Assignment find(Long id){
        return this.assignmentRepository.findById(id)
                        .orElseThrow(() -> new BadRequestException("No hay asignaciones por el id suministrado"));
    }


}
