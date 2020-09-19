package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Role;

/**
 * {@code JpaRepository} to handle {@code Role}
 * 
 * @author Thierry VILLEPREUX
 */
public interface RoleJpaRepository extends JpaRepository<Role, Long> {

    /**
     * Return default {@code Role} (USER)
     * 
     * @return default USER {@code Role}
     */
    Role findByDefaultRoleTrue();

    Role findByCode(String code);
}
