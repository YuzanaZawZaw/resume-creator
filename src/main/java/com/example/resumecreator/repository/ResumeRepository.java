package com.example.resumecreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resumecreator.model.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    // Custom query methods can be defined here if needed

}
