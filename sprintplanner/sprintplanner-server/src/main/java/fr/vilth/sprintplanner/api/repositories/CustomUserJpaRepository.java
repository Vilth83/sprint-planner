package fr.vilth.sprintplanner.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.CustomUser;

/**
 * {@code JpaRepository} to handle {@code CustomUser}
 * 
 * @author Thierry VILLEPREUX
 */
public interface CustomUserJpaRepository
	extends JpaRepository<CustomUser, Long> {

    /**
     * Retrieves the {@code CustomUser} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<CustomUser> findByUsername(String username);

    /**
     * Retrieves the current authenticated {@code CustomUser}.
     *
     * @param id user id
     * @return a projected view
     */
    Optional<CustomUser> getById(Long id);

    /**
     * Retrieves wether or not given username exists in database
     * 
     * @param username the given username to test
     * @return {@code true} if the username exists in database; {@code false}
     *         otherwise
     */
    boolean existsByUsernameIgnoreCase(String username);
}
