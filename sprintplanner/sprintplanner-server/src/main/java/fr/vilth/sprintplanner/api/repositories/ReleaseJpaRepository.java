package fr.vilth.sprintplanner.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Release;

/**
 * {@code JpaRepository} to handle {@code Release}
 * 
 * @author Thierry VILLEPREUX
 */
public interface ReleaseJpaRepository extends JpaRepository<Release, Long> {

    /**
     * Finds last {@code Release}.
     * <p>
     * Last release is defined by ordering by PI, sprint and week descending and
     * taking the first result.
     * 
     * @return the last {@code Release}
     */
    Release findFirstByOrderByPiDescSprintDescWeekDesc();

    /**
     * Retrieves all {@code release} ordered by PI, sprint and week descending.
     * 
     * @return a {@code List} of {@code Release}
     */
    List<Release> findAllByOrderByPiDescSprintDescWeekDesc();
}
