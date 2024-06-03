package com.riwi.vacantes.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacantes.entities.Company;
import com.riwi.vacantes.entities.Vacant;
import com.riwi.vacantes.repositories.CompanyRepository;
import com.riwi.vacantes.repositories.VacantRepository;
import com.riwi.vacantes.services.interfaces.IVacantService;
import com.riwi.vacantes.utils.dto.request.VacantRequest;
import com.riwi.vacantes.utils.dto.response.CompanyToVacantResponse;
import com.riwi.vacantes.utils.dto.response.VacantResponse;
import com.riwi.vacantes.utils.enums.Status;
import com.riwi.vacantes.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VacantService implements IVacantService{

    @Autowired
    private final VacantRepository vacantRepository;
    @Autowired
    private final CompanyRepository companyRepository;
    @Override
    public void delete(Long id) {
        Vacant vacant = this.find(id);
        this.vacantRepository.delete(vacant);
    }

    @Override
    public VacantResponse create(VacantRequest rq) {

        Company company = this.companyRepository.findById(rq.getCompanyId()).orElseThrow(() -> new IdNotFoundException("company"));
        Vacant vacant = this.requestToVacant(rq, new Vacant());
        vacant.setCompany(company);
        return this.vacantToResponse(this.vacantRepository.save(vacant));
    }

    @Override
    public VacantResponse update(Long id, VacantRequest rq) {
        Vacant vacant = this.find(id);
        Company company = this.companyRepository.findById(rq.getCompanyId()).orElseThrow(() -> new IdNotFoundException("Company"));
        this.requestToVacant(rq, vacant);

        vacant.setCompany(company);
        if (rq.getStatus_vacant() != null) {
            vacant.setStatus_vacant(rq.getStatus_vacant());
        }

        return this.vacantToResponse(this.vacantRepository.save(vacant));
    }

    @Override
    public Page<VacantResponse> getAll(int page, int size) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pageable = PageRequest.of(page, size);
        return this.vacantRepository.findAll(pageable)
                .map(this::vacantToResponse);
    }

    @Override
    public VacantResponse getById(Long id) {
        return this.vacantToResponse(this.find(id));
    }

    private VacantResponse vacantToResponse(Vacant entity){
        VacantResponse response = new VacantResponse();
        
        BeanUtils.copyProperties(entity, response);
        
        CompanyToVacantResponse companyResponse = new CompanyToVacantResponse();

        BeanUtils.copyProperties(entity.getCompany(), companyResponse);

        response.setCompany(companyResponse);

        return response;
    }

    private Vacant requestToVacant(VacantRequest rq, Vacant entity){

        BeanUtils.copyProperties(rq, entity);

        entity.setStatus_vacant(Status.ACTIVE);

        return entity;
    }

    private Vacant find(Long id){
        return this.vacantRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Vacant"));
    }
}
