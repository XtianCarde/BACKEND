package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.UserRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.UserBasicResp;

public interface IUserService extends CrudService<UserRequest, UserBasicResp, Long> {

}
