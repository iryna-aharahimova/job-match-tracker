package com.example.jobmatchtracker.repository;

import com.example.jobmatchtracker.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<SkillRepository, Long> {

    Optional<Skill> findByNameIgnoreCase(String name);

    List<Skill> findByNameIn(Collection<String> names);
}
