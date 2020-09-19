package fr.vilth.sprintplanner.api.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserCreateDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserManagementDto;

/**
 * Service to handle {@code CustomUserDetails}.
 * <p>
 * Extends {@link UserDetailsService} to benefit from {@code Spring} security
 * implementation.
 * 
 * @author Thierry VILLEPREUX
 */
public interface CustomUserDetailsService extends UserDetailsService {

    /**
     * Returns current authenticated {@code CustomUser}.
     * 
     * @param id the identifier of currently authenticated {@code CustomUser}
     * @return {@code CustomUserInfo} representing a {@code CustomUser}
     */
    CustomUserInfoDto getCurrentUserInfo(Long id);

    /**
     * Retrieves wether or not given username exists in database
     * 
     * @param username the given username to test
     * @return {@code true} if the username exists in database; {@code false}
     *         otherwise
     */
    boolean usernameIsUnique(String username);

    /**
     * Persist given {@code CustomUser}
     * 
     * @param inputs {@code CustomUserCreateDto} representing a
     *        {@code CustomUser} to persist
     */
    void create(CustomUserCreateDto inputs);

    List<CustomUserManagementDto> findAll();

    void toggleAccountActivation(Long id, CustomUserManagementDto inputs);

    void toggleAdminRole(Long id, CustomUserManagementDto inputs);
}
