package com.example.resumecreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resumecreator.model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long>{

}
