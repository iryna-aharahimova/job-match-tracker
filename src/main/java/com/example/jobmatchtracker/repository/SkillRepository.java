package com.example.jobmatchtracker.repository;

import com.example.jobmatchtracker.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> findBySkillNameIgnoreCase(String skillName);

    List<Skill> findBySkillNameIn(Collection<String> skillNames);
}
