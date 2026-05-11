package com.example.jobmatchtracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class EmailNotification extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_profile_id")
    private JobProfile jobProfile;

    @Column(nullable = false)
    private String emailSubject;

    @Column(columnDefinition = "TEXT")
    private String emailContent;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime sentAt;

    @Override
    public String toString() {
        return "EmailNotification(" +
                "id=" + getId() +
                ", emailSubject=" + emailSubject +
                ", sentAt=" + sentAt +
                ")";
    }
}
