package com.example.jobmatchtracker.entity;

import com.example.jobmatchtracker.enums.SearchRunStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SearchRun extends AbstractEntity {

    @Column(nullable = false)
    private String jobSource;

    @Column(nullable = false)
    private Integer jobsFetched = 0;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SearchRunStatus searchRunStatus;

    @Override
    public String toString() {
        return "SearchRun(" +
                "id=" + getId() +
                ", source=" + jobSource +
                ", jobsFetched=" + jobsFetched +
                ", status=" + searchRunStatus +
                ", startedAt=" + startedAt +
                ", finishedAt=" + finishedAt +
                ")";
    }
}
