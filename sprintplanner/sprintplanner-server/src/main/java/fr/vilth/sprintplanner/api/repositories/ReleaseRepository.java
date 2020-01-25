package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Release;

public interface ReleaseRepository extends JpaRepository<Release, Long> {

    Release findFirstByOrderByPiDescSprintDescWeekDesc();
}
