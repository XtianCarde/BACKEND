package com.riwi.PlataformaAprendizajeRiwi.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.SubmissionRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.SubmissionBasicResp;
import com.riwi.PlataformaAprendizajeRiwi.domain.entities.Submission;
import com.riwi.PlataformaAprendizajeRiwi.domain.repositories.SubmissionRepository;
import com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services.ISubmissionService;
import com.riwi.PlataformaAprendizajeRiwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionService implements ISubmissionService {

    @Autowired
    private final SubmissionRepository submissionRepository;

    @Override
    public SubmissionBasicResp create(SubmissionRequest rq) {
        Submission entity = this.requestToEntity(rq);

        return this.entityToResponse(this.submissionRepository.save(entity));
    }

    @Override
    public SubmissionBasicResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public SubmissionBasicResp update(SubmissionRequest rq, Long id) {
        Submission entity = this.find(id);
        entity = requestToEntity(rq);
        entity.setSubmissionId(id);

        return this.entityToResponse(this.submissionRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.submissionRepository.delete(this.find(id));
    }

    @Override
    public Page<SubmissionBasicResp> getAll(int page, int size) {
        if(page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.submissionRepository.findAll(pagination)
                            .map(this::entityToResponse);
    }
    
    private Submission requestToEntity(SubmissionRequest request){

        return Submission.builder()
                .content(request.getContent())
                .grade(request.getGrade())
                .build();
    }
    
    private SubmissionBasicResp entityToResponse(Submission entity){
        return SubmissionBasicResp.builder()
                    .submissionId(entity.getSubmissionId())
                    .content(entity.getContent())
                    .grade(entity.getGrade())
                    .submissionDate(entity.getSubmissionDate())
                    .build();
    }

    private Submission find(Long id){
        return this.submissionRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No existen tareas con el id suministrado"));
    }

}
