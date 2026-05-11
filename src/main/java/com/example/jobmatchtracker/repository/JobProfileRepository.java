package com.example.jobmatchtracker.repository;

import com.example.jobmatchtracker.entity.JobProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobProfileRepository extends JpaRepository<JobProfile, Long> {

    List<JobProfile> findByUserId(Long userId);

    Optional<JobProfile> findByIdAndUserId(Long id, Long userId);

    boolean existsByUserIdAndProfileNameIgnoreCase(Long userId, String profileName);
}
