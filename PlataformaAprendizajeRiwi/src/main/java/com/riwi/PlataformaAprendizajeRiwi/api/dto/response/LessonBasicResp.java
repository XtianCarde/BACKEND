package com.riwi.PlataformaAprendizajeRiwi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonBasicResp {
    
    private Long lessonId;
    private String lessonTitle;
    private String content;
}
