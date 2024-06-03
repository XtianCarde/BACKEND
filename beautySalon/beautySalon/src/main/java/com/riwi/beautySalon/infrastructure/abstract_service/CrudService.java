package com.riwi.beautySalon.infrastructure.abstract_service;

import org.springframework.data.domain.Page;

import com.riwi.beautySalon.utils.enums.SortType;

public interface CrudService<RQ, RS, ID> {
    RS create(RQ rq);

    RS get(ID id);

    RS update(RQ RQ, ID id);

    void delete(ID id);

    Page<RS> getAll(int page, int size, SortType sort);
}
