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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.resumecreator.dto.ResumeDTO;
import com.example.resumecreator.model.Education;
import com.example.resumecreator.model.Experience;
import com.example.resumecreator.model.Link;
import com.example.resumecreator.model.Resume;
import com.example.resumecreator.model.Skill;
import com.example.resumecreator.service.ResumeService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @GetMapping
    public List<ResumeDTO> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Resume> createResume(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("summary") String summary,
            @RequestParam(value = "currentPosition", required = false) String currentPosition,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {
        try {
            Resume resume = new Resume();
            resume.setName(name);
            resume.setEmail(email);
            resume.setPhone(phone);
            resume.setSummary(summary);
            resume.setCurrentPosition(currentPosition);

            if (photo != null && !photo.isEmpty()) {
                resume.setPhoto(photo.getBytes());
            }

            Resume savedResume = resumeService.saveResume(resume);
            return new ResponseEntity<>(savedResume, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateResume(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("summary") String summary,
            @RequestParam(value = "currentPosition", required = false) String currentPosition,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {
        return resumeService.getResumeById(id)
                .map(existingResume -> {
                    try {
                        existingResume.setName(name);
                        existingResume.setEmail(email);
                        existingResume.setPhone(phone);
                        existingResume.setSummary(summary);
                        existingResume.setCurrentPosition(currentPosition);
        
                        if (photo != null && !photo.isEmpty()) {
                            existingResume.setPhoto(photo.getBytes());
                        }
        
                        Resume updatedResume = resumeService.saveResume(existingResume);
                        return new ResponseEntity<>(updatedResume, HttpStatus.OK);
                    } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
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

    @PostMapping("/{resumeId}/links")
    public ResponseEntity<Link> addLink(@PathVariable Long resumeId, @RequestBody Link link) {
        return resumeService.getResumeById(resumeId)
                .map(resume -> {
                    link.setResume(resume);
                    Link savedLink = resumeService.saveLink(link);
                    return new ResponseEntity<>(savedLink, HttpStatus.CREATED);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{resumeId}/links")
    public ResponseEntity<?> getLinksByResumeId(@PathVariable Long resumeId) {
        return resumeService.getResumeById(resumeId)
                .map(resume -> new ResponseEntity<>(resumeService.getLinksByResumeId(resumeId), HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/links/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long id) {
        resumeService.deleteLink(id);
        return ResponseEntity.noContent().build();
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
