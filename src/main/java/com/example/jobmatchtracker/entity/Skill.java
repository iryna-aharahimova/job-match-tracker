package com.example.jobmatchtracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
public class Skill extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String skillName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill that)) return false;
        return Objects.equals(skillName, that.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillName);
    }

    @Override
    public String toString() {
        return "Skill(" +
                "id=" + getId() +
                ", skillName=" + skillName +
                ")";
    }
}
