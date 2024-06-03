package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import org.springframework.data.domain.Page;

public interface CrudService<RQ, RS, ID> {

    RS create(RQ rq);

    RS get(ID id);

    RS update(RQ rq, ID id);

    void delete(ID id);
    
    Page<RS> getAll(int page, int size);
}
