package com.riwi.PlataformaAprendizajeRiwi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CourseBasicResp {
    
    private Long courseId;
    private String courseName;
    private String description;
    private UserBasicResp userInstructor;
}
