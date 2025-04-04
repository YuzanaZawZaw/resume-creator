package com.example.resumecreator.service;

import com.example.resumecreator.model.Education;
import com.example.resumecreator.model.Experience;
import com.example.resumecreator.model.Resume;
import com.example.resumecreator.model.Skill;
import com.example.resumecreator.repository.EducationRepository;
import com.example.resumecreator.repository.ExperienceRepository;
import com.example.resumecreator.repository.ResumeRepository;
import com.example.resumecreator.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private SkillRepository skillRepository;

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    public Optional<Resume> getResumeById(Long id) {
        return resumeRepository.findById(id);
    }

    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }

    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }

    public Experience saveExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

}