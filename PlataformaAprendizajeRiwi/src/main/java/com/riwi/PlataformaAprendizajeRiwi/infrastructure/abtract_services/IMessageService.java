package com.riwi.PlataformaAprendizajeRiwi.infrastructure.abtract_services;

import com.riwi.PlataformaAprendizajeRiwi.api.dto.request.MessageRequest;
import com.riwi.PlataformaAprendizajeRiwi.api.dto.response.MessageBasicResp;

public interface IMessageService extends CrudService<MessageRequest, MessageBasicResp, Long> {
    
}
