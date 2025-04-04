package com.example.resumecreator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.resumecreator.model.Education;
import com.example.resumecreator.model.Experience;
import com.example.resumecreator.model.Resume;
import com.example.resumecreator.model.Skill;
import com.example.resumecreator.service.ResumeService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @GetMapping
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Resume createResume(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resume> updateResume(@PathVariable Long id, @RequestBody Resume resume) {
        return resumeService.getResumeById(id)
                .map(existingResume -> {
                    resume.setId(id);
                    Resume updatedResume = resumeService.saveResume(resume);
                    return new ResponseEntity<>(updatedResume, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{resumeId}/educations")
    public ResponseEntity<Education> addEducation(@PathVariable Long resumeId, @RequestBody Education education) {
        return resumeService.getResumeById(resumeId)
                .map(resume -> {
                    education.setResume(resume);
                    Education savedEducation = resumeService.saveEducation(education);
                    return new ResponseEntity<>(savedEducation, HttpStatus.CREATED);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{resumeId}/experiences")
    public ResponseEntity<Experience> addExperience(@PathVariable Long resumeId, @RequestBody Experience experience) {
        return resumeService.getResumeById(resumeId)
                .map(resume -> {
                    experience.setResume(resume);
                    Experience savedExperience = resumeService.saveExperience(experience);
                    return new ResponseEntity<>(savedExperience, HttpStatus.CREATED);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{resumeId}/skills")
    public ResponseEntity<Skill> addSkill(@PathVariable Long resumeId, @RequestBody Skill skill) {
        return resumeService.getResumeById(resumeId)
                .map(resume -> {
                    skill.setResume(resume);
                    Skill savedSkill = resumeService.saveSkill(skill);
                    return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/educations/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        resumeService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/experiences/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        resumeService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        resumeService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
