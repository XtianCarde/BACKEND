package com.riwi.PlataformaAprendizajeRiwi.api.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentBasicResp {
    private Long assignmentId;
    private String assignmentTitle;
    private String description;
    private LocalDateTime dueDate;
    private LessonBasicResp lesson;
}
