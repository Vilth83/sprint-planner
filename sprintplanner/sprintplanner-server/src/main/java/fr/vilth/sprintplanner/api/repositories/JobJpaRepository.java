package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Job;

public interface JobJpaRepository extends JpaRepository<Job, Long> {

    Job findByTitleAndTask(String title, String taskName);
}
