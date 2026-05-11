package com.example.jobmatchtracker.dto;

import com.example.jobmatchtracker.enums.JobGrade;
import com.example.jobmatchtracker.enums.WorkMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class JobProfileRequest {

    @NotBlank(message = "Profile name is required")
    private String profileName;

    private String skillsText;

    @NotEmpty(message = "Select at least one preferred grade")
    private Set<JobGrade> preferredGrades = new HashSet<>();

    @NotEmpty(message = "Select at least one work mode")
    private Set<WorkMode> workModes = new HashSet<>();
}
