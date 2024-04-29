package com.riwi.vacantes.services.interfaces;

import org.springframework.data.domain.Page;

public interface CrudServices<RQ, RS, ID> {
    
    void delete(ID id);

    RS create(RQ rq);

    RS update(ID id, RQ rq);

    Page<RS> getAll(int page, int size);
}
