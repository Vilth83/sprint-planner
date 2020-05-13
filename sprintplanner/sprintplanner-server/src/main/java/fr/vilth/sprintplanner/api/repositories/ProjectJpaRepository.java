package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Project;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {

    Project findByTrigram(String trigram);
}