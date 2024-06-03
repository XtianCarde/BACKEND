package com.riwi.vacantes.services;

import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.riwi.vacantes.entities.Company;
import com.riwi.vacantes.entities.Vacant;
import com.riwi.vacantes.repositories.CompanyRepository;
import com.riwi.vacantes.services.interfaces.ICompanyService;
import com.riwi.vacantes.utils.dto.request.CompanyRequest;
import com.riwi.vacantes.utils.dto.response.CompanyResponse;
import com.riwi.vacantes.utils.dto.response.VacantToCompanyResponse;
import com.riwi.vacantes.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyServices implements ICompanyService {

    @Autowired
    private final CompanyRepository objCompanyRepository;

    @Override
    public CompanyResponse create(CompanyRequest rq) {
        Company company = this.requestToCompany(rq, new Company());
        return this.entityToResponse(this.objCompanyRepository.save(company));
    }

    @Override
    public void delete(String id) {
       Company objCompany = this.find(id);
       this.objCompanyRepository.delete(objCompany);
    }

    @Override
    public Page<CompanyResponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.objCompanyRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    @Override
    public CompanyResponse getById(String id) {
        return this.entityToResponse(this.find(id));
    }

    private Company find(String id){
        return this.objCompanyRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Company"));
    }

    @Override
    public CompanyResponse update(String id, CompanyRequest rq) {
        Company company = this.find(id);
        Company companyUpdate = this.requestToCompany(rq, company);
        return this.entityToResponse(this.objCompanyRepository.save(companyUpdate));
    }
    
    private CompanyResponse entityToResponse(Company entity){
        CompanyResponse response = new CompanyResponse();
        //response.setId(entity.getId());
        //response.setLocation(entity.getLocation());
        //response.setContact(entity.getContact());
        //response.setName(entity.getName());
        //response.setVacantes(entity.getVacantes());

        BeanUtils.copyProperties(entity, response);

        response.setVacantes(entity.getVacantes()
        .stream()
        .map(this::vacantToResponse)
        .collect(Collectors.toList()));
        return response;
    }

    private VacantToCompanyResponse vacantToResponse(Vacant entity){
        VacantToCompanyResponse response = new VacantToCompanyResponse();
        BeanUtils.copyProperties(entity, response);

        return response;
    }

    private Company requestToCompany(CompanyRequest CR, Company company){
        BeanUtils.copyProperties(CR, company);
        company.setVacantes(new ArrayList<>());
        return company;
    }


}
