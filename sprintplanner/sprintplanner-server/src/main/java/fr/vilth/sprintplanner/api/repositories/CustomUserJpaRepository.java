package fr.vilth.sprintplanner.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserAuthDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;
import fr.vilth.sprintplanner.domain.entities.CustomUser;

/**
 * {@code JpaRepository} to handle {@code CustomUser}
 * 
 * @author Thierry VILLEPREUX
 */
public interface CustomUserJpaRepository
	extends JpaRepository<CustomUser, Long> {

    /**
     * Retrieves a projected view of the {@code CustomUser} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<CustomUserAuthDto> findByUsername(String username);

    /**
     * Retrieves a projected view of the current authenticated
     * {@code CustomUser}.
     *
     * @param id user id
     * @return a projected view
     */
    Optional<CustomUserInfoDto> getById(Long id);

    /**
     * Retrieves wether or not given username exists in database
     * 
     * @param username the given username to test
     * @return {@code true} if the username exists in database; {@code false}
     *         otherwise
     */
    boolean existsByUsernameIgnoreCase(String username);
}
