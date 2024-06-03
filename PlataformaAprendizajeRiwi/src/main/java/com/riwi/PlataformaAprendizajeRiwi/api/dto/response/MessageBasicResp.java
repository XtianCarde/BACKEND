package com.riwi.PlataformaAprendizajeRiwi.api.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageBasicResp {
    private Long messageId;
    private UserBasicResp userSender;
    private UserBasicResp userReceiver;
    private CourseBasicResp course;
    private LocalDateTime sentDate;
}
