package com.example.resumecreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resumecreator.model.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long>{

}
