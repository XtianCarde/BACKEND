package com.riwi.PlataformaAprendizajeRiwi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentBasicResp {
    private Long enrollmentId;
    private UserBasicResp student;
    private CourseBasicResp course;
}
