package com.example.jobmatchtracker.repository;

import com.example.jobmatchtracker.entity.JobPosting;
import com.example.jobmatchtracker.enums.JobGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    Optional<JobPosting> findByJobUrl(String jobUrl);

    boolean existsByJobUrl(String jobUrl);

    List<JobPosting> findByJobSource(String jobSource);

    List<JobPosting> findByJobGradeIn(Collection<JobGrade> jobGrades);
}
