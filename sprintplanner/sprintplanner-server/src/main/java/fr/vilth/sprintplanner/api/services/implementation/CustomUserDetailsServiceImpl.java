package fr.vilth.sprintplanner.api.services.implementation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.CustomUserJpaRepository;
import fr.vilth.sprintplanner.api.repositories.RoleJpaRepository;
import fr.vilth.sprintplanner.api.services.CustomUserDetailsService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.commons.exceptions.ResourceNotFoundException;
import fr.vilth.sprintplanner.commons.security.CustomUserDetails;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserAuthDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserCreateDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;
import fr.vilth.sprintplanner.domain.entities.CustomUser;
import fr.vilth.sprintplanner.domain.entities.Role;

/**
 * Implementation of {@code CustomUserDetailsService}
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class CustomUserDetailsServiceImpl extends AbstractService
	implements CustomUserDetailsService {

    private final CustomUserJpaRepository userRepository;

    private final RoleJpaRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    protected CustomUserDetailsServiceImpl(
	    CustomUserJpaRepository userRepository,
	    RoleJpaRepository roleRepository,
	    PasswordEncoder passwordEncoder) {
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
	this.passwordEncoder = passwordEncoder;
    }

    // Throws UsernameNotFoundException (Spring contract)
    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	CustomUserAuthDto user = userRepository.findByUsername(username)
		.map(customUser -> convert(customUser, CustomUserAuthDto.class))
		.orElseThrow(() -> new UsernameNotFoundException(
			"no user found with username: " + username));
	return new CustomUserDetails(user);
    }

    // Throws ResourceNotFoundException (restful practice)
    @Override
    public CustomUserInfoDto getCurrentUserInfo(Long id) {
	return userRepository.getById(id)
		.map(customUser -> convert(customUser, CustomUserInfoDto.class))
		.orElseThrow(
			() -> new ResourceNotFoundException("with id:" + id));
    }

    @Override
    public void create(CustomUserCreateDto dto) {
	String encoded = passwordEncoder.encode(dto.getPassword());
	Role role = roleRepository.findByDefaultRoleTrue();
	Set<Role> roles = new HashSet<>();
	roles.add(role);
	dto.setPassword(encoded);
	CustomUser customUser = new CustomUser(encoded, dto.getUsername(),
		dto.getFirstname(), dto.getLastname(), roles);
	userRepository.save(customUser);
    }

    @Override
    public boolean usernameIsUnique(String username) {
	return !userRepository.existsByUsernameIgnoreCase(username);
    }
}
