package com.example.jobmatchtracker.repository;

import com.example.jobmatchtracker.entity.JobMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobMatchRepository extends JpaRepository<JobMatch, Long> {

    List<JobMatch> findByUserId(Long userId);

    List<JobMatch> findByJobProfileId(Long jobProfileId);

    List<JobMatch> findByJobProfileIdOrderByJobMatchPercentageDesc(Long jobProfileId);

    Optional<JobMatch> findByJobProfileIdAndJobPostingId(Long jobProfileId, Long jobPostingId);

    boolean existsByJobProfileIdAndJobPostingId(Long jobProfileId, Long jobPostingId);
}
