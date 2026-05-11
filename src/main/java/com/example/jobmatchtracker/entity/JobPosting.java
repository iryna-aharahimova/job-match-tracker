package com.example.jobmatchtracker.entity;

import com.example.jobmatchtracker.enums.JobGrade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobPosting extends AbstractEntity {

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String company;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;

    @Column(nullable = false, unique = true)
    private String jobUrl;

    @Column(nullable = false)
    private String jobSource;

    @Enumerated(EnumType.STRING)
    private JobGrade jobGrade;

    @ManyToMany
    @JoinTable(
            name = "job_posting_required_skills",
            joinColumns = @JoinColumn(name = "job_posting_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> requiredSkills = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobPosting that)) return false;
        return Objects.equals(jobUrl, that.jobUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobUrl);
    }

    @Override
    public String toString() {
        return "JobPosting(" +
                "id=" + getId() +
                ", title=" + jobTitle +
                ", company=" + company +
                ", jobUrl=" + jobUrl +
                ", jobSource=" + jobSource +
                ", jobGrade=" + jobGrade +
                ", createdAt=" + createdAt +
                ")";
    }
}
