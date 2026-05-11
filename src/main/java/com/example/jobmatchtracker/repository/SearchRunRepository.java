package com.example.jobmatchtracker.repository;

import com.example.jobmatchtracker.entity.SearchRun;
import com.example.jobmatchtracker.enums.SearchRunStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRunRepository extends JpaRepository<SearchRun, Long> {

    List<SearchRun> findByJobSource(String jobSource);

    List<SearchRun> findBySearchRunStatus(SearchRunStatus searchRunStatus);
}
