package com.example.jobmatchtracker.controller;

import com.example.jobmatchtracker.dto.JobProfileRequest;
import com.example.jobmatchtracker.entity.JobProfile;
import com.example.jobmatchtracker.entity.Skill;
import com.example.jobmatchtracker.enums.JobGrade;
import com.example.jobmatchtracker.enums.WorkMode;
import com.example.jobmatchtracker.service.JobProfileService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profiles")
public class JobProfileController {

    // TODO: replace with authenticated user once Spring Security is added
    private static final Long CURRENT_USER_ID = 1L;

    private final JobProfileService jobProfileService;

    public JobProfileController(JobProfileService jobProfileService) {
        this.jobProfileService = jobProfileService;
    }

    @GetMapping
    public String list(Model model) {
        List<JobProfile> profiles = jobProfileService.getProfilesForUser(CURRENT_USER_ID);
        model.addAttribute("profiles", profiles);
        return "profiles/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("jobProfileRequest", new JobProfileRequest());
        addFormReferenceData(model);
        return "profiles/create";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("jobProfileRequest") JobProfileRequest request,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            addFormReferenceData(model);
            return "profiles/create";
        }
        jobProfileService.createProfile(CURRENT_USER_ID, request);
        return "redirect:/profiles";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        JobProfile profile = jobProfileService.getProfile(CURRENT_USER_ID, id);
        model.addAttribute("jobProfileRequest", toRequest(profile));
        model.addAttribute("profileId", id);
        addFormReferenceData(model);
        return "profiles/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("jobProfileRequest") JobProfileRequest request,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("profileId", id);
            addFormReferenceData(model);
            return "profiles/edit";
        }
        jobProfileService.updateProfile(CURRENT_USER_ID, id, request);
        return "redirect:/profiles";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        jobProfileService.deleteProfile(CURRENT_USER_ID, id);
        return "redirect:/profiles";
    }

    private void addFormReferenceData(Model model) {
        model.addAttribute("grades", JobGrade.values());
        model.addAttribute("workModes", WorkMode.values());
    }

    private JobProfileRequest toRequest(JobProfile profile) {
        JobProfileRequest request = new JobProfileRequest();
        request.setProfileName(profile.getProfileName());
        request.setSkillsText(profile.getSkills().stream()
                .map(Skill::getSkillName)
                .collect(Collectors.joining(", ")));
        request.setPreferredGrades(new HashSet<>(profile.getPreferredGrades()));
        request.setWorkModes(new HashSet<>(profile.getWorkModes()));
        return request;
    }
}
