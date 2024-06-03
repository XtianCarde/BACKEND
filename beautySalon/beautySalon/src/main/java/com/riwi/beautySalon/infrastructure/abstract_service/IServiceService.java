package com.riwi.beautySalon.infrastructure.abstract_service;

import com.riwi.beautySalon.api.dto.request.ServiceReq;
import com.riwi.beautySalon.api.dto.response.ServiceResp;

public interface IServiceService extends CrudService<ServiceReq,ServiceResp,Long> {
    public final String FIELD_BY_SORT = "price";
        
}
