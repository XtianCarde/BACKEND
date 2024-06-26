package com.riwi.PlataformaAprendizajeRiwi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LessonBasicResp {
    
    private Long lessonId;
    private String lessonTitle;
    private String content;
}
