package com.riwi.PlataformaAprendizajeRiwi.domain.entities;

import java.util.List;
import com.riwi.PlataformaAprendizajeRiwi.util.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private Role role;
    
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "student",
    orphanRemoval = false)
    private List<Enrollment> student;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "user",
    orphanRemoval = false)
    private List<Submission> submissions;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "userSender",
    orphanRemoval = false)
    private List<Message> messagesSender;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "userReceiver",
    orphanRemoval = false)
    private List<Message> messagesReceiver;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "instructor",
    orphanRemoval = false)
    private List<Course> courses; 
}
