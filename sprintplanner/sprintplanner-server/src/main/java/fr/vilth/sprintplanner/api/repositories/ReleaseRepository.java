package fr.vilth.sprintplanner.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Release;

public interface ReleaseRepository extends JpaRepository<Release, Long> {

    Release findFirstByOrderByPiDescSprintDescWeekDesc();

    List<Release> findAllByOrderByPiDescSprintDescWeekDesc();
}
