package com.riwi.PlataformaAprendizajeRiwi.domain.entities;

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

@Entity(name = "course")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    @Lob
    private String description;
    
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "course",
    orphanRemoval = false)
    private List<Enrollment> student;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "course",
    orphanRemoval = false)
    private List<Lesson> lessons;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "course",
    orphanRemoval = false)
    private List<Message> messages;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "userId")
    private User instructor;
}
