package com.example.jobmatchtracker.service;

import com.example.jobmatchtracker.dto.JobProfileRequest;
import com.example.jobmatchtracker.entity.JobProfile;
import com.example.jobmatchtracker.entity.Skill;
import com.example.jobmatchtracker.entity.User;
import com.example.jobmatchtracker.repository.JobProfileRepository;
import com.example.jobmatchtracker.repository.SkillRepository;
import com.example.jobmatchtracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobProfileService {

    private final UserRepository userRepository;
    private final JobProfileRepository jobProfileRepository;
    private final SkillRepository skillRepository;

    public JobProfileService(UserRepository userRepository,
                             JobProfileRepository jobProfileRepository,
                             SkillRepository skillRepository) {
        this.userRepository = userRepository;
        this.jobProfileRepository = jobProfileRepository;
        this.skillRepository = skillRepository;
    }

    public List<JobProfile> getProfilesForUser(Long userId) {
        return jobProfileRepository.findByUserId(userId);
    }

    public JobProfile getProfile(Long userId, Long profileId) {
        return jobProfileRepository.findByIdAndUserId(profileId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found: " + profileId));
    }

    @Transactional
    public JobProfile createProfile(Long userId, JobProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        JobProfile profile = new JobProfile();
        profile.setUser(user);
        applyRequest(profile, request);
        return jobProfileRepository.save(profile);
    }

    @Transactional
    public JobProfile updateProfile(Long userId, Long profileId, JobProfileRequest request) {
        JobProfile profile = getProfile(userId, profileId);
        applyRequest(profile, request);
        return jobProfileRepository.save(profile);
    }

    @Transactional
    public void deleteProfile(Long userId, Long profileId) {
        JobProfile profile = getProfile(userId, profileId);
        jobProfileRepository.delete(profile);
    }

    private void applyRequest(JobProfile profile, JobProfileRequest request) {
        profile.setProfileName(request.getProfileName());
        profile.setPreferredGrades(new HashSet<>(request.getPreferredGrades()));
        profile.setWorkModes(new HashSet<>(request.getWorkModes()));
        profile.setSkills(resolveSkills(request.getSkillsText()));
    }

    private Set<Skill> resolveSkills(String skillsText) {
        Set<Skill> skills = new HashSet<>();
        if (skillsText == null || skillsText.isBlank()) {
            return skills;
        }
        Arrays.stream(skillsText.split(","))
                .map(String::trim)
                .filter(name -> !name.isBlank())
                .forEach(name -> {
                    Skill skill = skillRepository.findBySkillNameIgnoreCase(name)
                            .orElseGet(() -> {
                                Skill s = new Skill();
                                s.setSkillName(name);
                                return skillRepository.save(s);
                            });
                    skills.add(skill);
                });
        return skills;
    }
}
