package com.example.resumecreator.service;

import com.example.resumecreator.dto.EducationDTO;
import com.example.resumecreator.dto.ExperienceDTO;
import com.example.resumecreator.dto.ResumeDTO;
import com.example.resumecreator.dto.SkillDTO;
import com.example.resumecreator.dto.LinkDTO;
import com.example.resumecreator.model.Education;
import com.example.resumecreator.model.Experience;
import com.example.resumecreator.model.Link;
import com.example.resumecreator.model.Resume;
import com.example.resumecreator.model.Skill;
import com.example.resumecreator.repository.EducationRepository;
import com.example.resumecreator.repository.ExperienceRepository;
import com.example.resumecreator.repository.LinkRepository;
import com.example.resumecreator.repository.ResumeRepository;
import com.example.resumecreator.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private LinkRepository linkRepository;

    public List<ResumeDTO> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return resumes.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<Resume> getResumeById(Long id) {
        return resumeRepository.findById(id);
    }

    public Resume saveResumeDTO(ResumeDTO resume) {
        Resume resumeEntity = new Resume();
        resumeEntity.setName(resume.getName());
        resumeEntity.setEmail(resume.getEmail());
        resumeEntity.setPhone(resume.getPhone());
        resumeEntity.setSummary(resume.getSummary());
        resumeEntity.setPhoto(resume.getPhoto());
        return resumeRepository.save(resumeEntity);
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

    public ResumeDTO mapToDTO(Resume resume) {
        ResumeDTO dto = new ResumeDTO();
        dto.setId(resume.getId());
        dto.setName(resume.getName());
        dto.setEmail(resume.getEmail());
        dto.setPhone(resume.getPhone());
        dto.setSummary(resume.getSummary());
        dto.setCurrentPosition(resume.getCurrentPosition());
        dto.setEducations(resume.getEducations().stream().map(education -> {
            EducationDTO educationDTO = new EducationDTO();
            educationDTO.setId(education.getId());
            educationDTO.setDegree(education.getDegree());
            educationDTO.setInstitution(education.getInstitution());
            return educationDTO;
        }).collect(Collectors.toList()));
        dto.setExperiences(resume.getExperiences().stream().map(experience -> {
            ExperienceDTO experienceDTO = new ExperienceDTO();
            experienceDTO.setId(experience.getId());
            experienceDTO.setJobTitle(experience.getJobTitle());
            experienceDTO.setCompany(experience.getCompany());
            return experienceDTO;
        }).collect(Collectors.toList()));
        dto.setSkills(resume.getSkills().stream().map(skill -> {
            SkillDTO skillDTO = new SkillDTO();
            skillDTO.setId(skill.getId());
            skillDTO.setName(skill.getName());
            skillDTO.setTitle(skill.getTitle());
            return skillDTO;
        }).collect(Collectors.toList()));
        dto.setLinks(resume.getLinks().stream().map(link -> {
            LinkDTO linkDTO = new LinkDTO();
            linkDTO.setId(link.getId());
            linkDTO.setUrl(link.getUrl());
            linkDTO.setDescription(link.getDescription());
            return linkDTO;
        }).collect(Collectors.toList()));
        return dto;
    }

    public Object getLinksByResumeId(Long resumeId) {
        return linkRepository.findById(resumeId);
    }

    public void deleteLink(Long id) {
        linkRepository.deleteById(id);
    }

    public Link saveLink(Link link) {
        return linkRepository.save(link);
    }

}