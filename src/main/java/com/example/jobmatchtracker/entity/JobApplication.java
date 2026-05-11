package com.example.jobmatchtracker.entity;

import com.example.jobmatchtracker.enums.JobApplicationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobApplication extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_profile_id", nullable = false)
    private JobProfile jobProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id", nullable = false)
    private JobPosting jobPosting;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobApplicationStatus jobApplicationStatus;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime appliedAt;

    @Override
    public String toString() {
        return "JobApplication(" +
                "id=" + getId() +
                ", status=" + jobApplicationStatus +
                ", appliedAt=" + appliedAt +
                ")";
    }
}
