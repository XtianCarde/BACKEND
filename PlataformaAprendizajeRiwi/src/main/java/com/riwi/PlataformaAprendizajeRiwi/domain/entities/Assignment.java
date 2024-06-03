package com.riwi.PlataformaAprendizajeRiwi.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "assignment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;
    @Column(nullable = false)
    private String assignmentTitle;
    @Column(nullable = false)
    @Lob
    private String description;
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime dueDate = LocalDateTime.now();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", referencedColumnName = "lessonId")
    private Lesson lesson;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "assignment",
    orphanRemoval = false)
    private List<Submission> submissions;
}
