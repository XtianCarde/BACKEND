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
public class SubmissionBasicResp {
    private Long submissionId;
    private Double grade;
    private UserBasicResp user;
    private String content;
    private LocalDateTime submissionDate;
    private AssignmentBasicResp assignment;
}
