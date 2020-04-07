package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Role;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {

    Role findByDefaultRoleTrue();
}
