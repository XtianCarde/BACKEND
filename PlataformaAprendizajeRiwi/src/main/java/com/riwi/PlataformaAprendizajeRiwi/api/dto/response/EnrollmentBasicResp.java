package com.riwi.PlataformaAprendizajeRiwi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentBasicResp {
    private Long enrollmentId;
    private UserBasicResp student;
    private CourseBasicResp course;
}
