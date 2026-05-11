package com.example.jobmatchtracker.entity;

import com.example.jobmatchtracker.enums.JobGrade;
import com.example.jobmatchtracker.enums.WorkMode;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobProfile extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String profileName;

    @ManyToMany
    @JoinTable(
            name = "job_profile_skills",
            joinColumns = @JoinColumn(name = "job_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    @ElementCollection(targetClass = JobGrade.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "job_profile_preferred_grades",
            joinColumns = @JoinColumn(name = "job_profile_id")
    )
    @Column(name = "grade")
    private Set<JobGrade> preferredGrades = new HashSet<>();

    @ElementCollection(targetClass = WorkMode.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "job_profile_work_modes",
            joinColumns = @JoinColumn(name = "job_profile_id")
    )
    @Column(name = "work_mode")
    private Set<WorkMode> workModes = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "JobProfile(" +
                "id=" + getId() +
                ", profileName=" + profileName +
                ", createdAt=" + createdAt +
                ")";
    }
}
