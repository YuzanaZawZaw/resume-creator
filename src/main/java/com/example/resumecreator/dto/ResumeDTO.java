package com.example.resumecreator.dto;

import java.util.List;

public class ResumeDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String summary;
    private byte[] photo;
    private String currentPosition;
    public String getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    private List<EducationDTO> educations;
    private List<ExperienceDTO> experiences;
    private List<SkillDTO> skills;
    private List<LinkDTO> links;
    public List<LinkDTO> getLinks() {
        return links;
    }
    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public List<EducationDTO> getEducations() {
        return educations;
    }
    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }
    public List<ExperienceDTO> getExperiences() {
        return experiences;
    }
    public void setExperiences(List<ExperienceDTO> experiences) {
        this.experiences = experiences;
    }
    public List<SkillDTO> getSkills() {
        return skills;
    }
    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }
    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public ResumeDTO() {
    }

    public ResumeDTO(Long id, String name, String email, String phone, String summary, byte[] photo,String currentPostion) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.summary = summary;
        this.photo = photo;
        this.currentPosition = currentPostion;
    }
}
