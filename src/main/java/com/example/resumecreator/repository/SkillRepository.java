package com.example.resumecreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resumecreator.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

}
