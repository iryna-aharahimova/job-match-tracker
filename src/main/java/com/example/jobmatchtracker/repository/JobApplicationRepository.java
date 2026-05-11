package com.example.jobmatchtracker.repository;

import com.example.jobmatchtracker.entity.JobApplication;
import com.example.jobmatchtracker.enums.JobApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByUserId(Long userId);

    List<JobApplication> findByJobProfileId(Long jobProfileId);

    List<JobApplication> findByJobProfileIdAndJobApplicationStatus(Long jobProfileId, JobApplicationStatus jobApplicationStatus);

    Optional<JobApplication> findByJobProfileIdAndJobPostingId(Long jobProfileId, Long jobPostingId);

    boolean existsByJobProfileIdAndJobPostingId(Long jobProfileId, Long jobPostingId);
}
